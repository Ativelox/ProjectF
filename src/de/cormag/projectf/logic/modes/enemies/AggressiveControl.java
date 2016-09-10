package de.cormag.projectf.logic.modes.enemies;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Optional;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.entities.properties.ILively;
import de.cormag.projectf.entities.properties.ISpatial;
import de.cormag.projectf.entities.properties.offensive.ICanOffensive;
import de.cormag.projectf.entities.properties.offensive.IOffensiveable;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.movement.IMoveBehavior;
import de.cormag.projectf.logic.offensive.IOffensiveBehavior;
import de.cormag.projectf.main.Handler;

/**
 * Controls the behavior of objects by actively searching and for enemies and
 * automatically attacking them.
 * 
 * @author Zabuza
 *
 */
public final class AggressiveControl implements IModeControl {
	/**
	 * Minimal distance to target in pixel for an offensive action.
	 */
	private static final float MINIMAL_TRIGGER_DISTANCE = 50;

	/**
	 * Error message when trying to pass a object that is missing interfaces.
	 */
	protected static final String ERROR_MISSING_INTERFACE = "The given object must implement ICanOffensive and ICanMove, see the documentation for more information.";

	/**
	 * Whether the mode currently is active or not. Controlled by
	 * {@link #activate()} and {@link #terminate()}
	 */
	private boolean mIsActive;
	/**
	 * Move order receiving behavior object.
	 */
	private final IMoveBehavior mMoveReceiver;
	/**
	 * Offensive order receiving behavior.
	 */
	private final IOffensiveBehavior mOffensiveReceiver;
	/**
	 * If present, current target object to be offensive towards. If not
	 * present, there is no target.
	 */
	private Optional<IOffensiveable> mOffensiveTarget;
	/**
	 * The entity which is controlled by this object.
	 */
	private final ISpatial mParent;
	
	private final Handler mHandler;

	/**
	 * Creates a new offensive movement control object which lets an object
	 * search for enemies and automatically target and attack enemies in sight.
	 * 
	 * @param parent
	 *            The entity which is controlled by this object. Must implement
	 *            {@link ICanOffensive} and {@link ICanMove}.
	 * @param moveReceiver
	 *            Move order receiving behavior object
	 * @param offensiveReceiver
	 *            Offensive order receiving behavior
	 */
	public AggressiveControl(final ISpatial parent, final IMoveBehavior moveReceiver,
			final IOffensiveBehavior offensiveReceiver, final Handler handler) {
		mParent = parent;
		mHandler = handler;
		mMoveReceiver = moveReceiver;
		mOffensiveReceiver = offensiveReceiver;
		mIsActive = false;
		mOffensiveTarget = Optional.empty();

		if (!(mParent instanceof ICanOffensive && mParent instanceof ICanMove)) {
			throw new IllegalArgumentException(ERROR_MISSING_INTERFACE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.modes.IModeControl#activate()
	 */
	@Override
	public void activate() {
		mIsActive = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.modes.IModeControl#terminate()
	 */
	@Override
	public void terminate() {
		mIsActive = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update() {
		// Pass update call to the receivers
		mMoveReceiver.update();
		mOffensiveReceiver.update();

		// Do not do update the control if not activated
		if (!mIsActive) {
			return;
		}

		// Case 1: Object is not attacking and chasing another object.
		if (!mOffensiveTarget.isPresent() && !mMoveReceiver.isMoving()) {
			
			IOffensiveable target = null;
			
			List<Entity> entityList = mHandler.getWorld().getEntityManager().getEntityList();
			
			for(Entity e : entityList){
				if(e instanceof IOffensiveable){
					if(((Enemy) mParent).getVisionField().intersects(e.getProperCollisionRectangle())){
						target = (IOffensiveable) e;
						
					}
				}
			}
			
			if (target != null) {
				mOffensiveTarget = Optional.of(target);

				// Try to follow the target
				if (target instanceof ICanMove) {
					mMoveReceiver.follow((ICanMove) target);
				}
			}
		}

		// Case 2: Object is trying to attack another object.
		if (mOffensiveTarget.isPresent()) {
			IOffensiveable target = mOffensiveTarget.get();

			boolean isCloseEnough = true;
			if (target instanceof ISpatial) {
				ISpatial targetAsSpatial = (ISpatial) target;
				Point2D targetPos = new Point2D.Float(targetAsSpatial.getRelativeX(), targetAsSpatial.getRelativeY());
				Point2D parentPos = new Point2D.Float(mParent.getRelativeX(), mParent.getRelativeY());
				double distance = targetPos.distance(parentPos);
				isCloseEnough = distance <= MINIMAL_TRIGGER_DISTANCE;
			}

			// Trigger offensive action or continue following if not close
			// enough already
			if (isCloseEnough && !mOffensiveReceiver.isOffensive()) {
				if (target instanceof ILively && ((ILively) target).isAlive()) {
					// Stop movement and cast an offensive attack
					mMoveReceiver.stopMovement();
					mOffensiveReceiver.offensiveAction(target);
				} else {
					// Target died, deselect and stop chasing it.
					mOffensiveTarget = Optional.empty();
					mMoveReceiver.stopMovement();
				}
			}
		}
	}
}
