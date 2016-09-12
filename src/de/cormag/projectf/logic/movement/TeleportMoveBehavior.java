package de.cormag.projectf.logic.movement;

import java.awt.geom.Point2D;
import java.util.Optional;

import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Behavior which resolves movement by direct teleporting.
 * 
 * @author Zabuza
 *
 */
public final class TeleportMoveBehavior implements IMoveBehavior {

	/**
	 * Radius of the arrival zone around the current destination. When an object
	 * enters this zone, the destination is considered reached.
	 */
	private static final float ARRIVAL_RADIUS = 30;

	/**
	 * If present, the target to follow.
	 */
	private Optional<ICanMove> mFollowTarget;
	/**
	 * Parent object this behavior belongs to.
	 */
	private final ICanMove mParent;
	/**
	 * If present, the current destination as point to move to.
	 */
	private Optional<Point2D> mCurrentDestination;
	/**
	 * Whether the object is currently moving.
	 */
	private boolean mIsMoving;

	/**
	 * Creates a new behavior which belongs to the given parent.
	 * 
	 * @param parent
	 *            Parent the behavior belongs to
	 */
	public TeleportMoveBehavior(final ICanMove parent) {
		mParent = parent;

		mFollowTarget = Optional.empty();
		mCurrentDestination = Optional.empty();
		
		mIsMoving = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cormag.projectf.logic.movement.IMoveBehavior#follow(de.cormag.projectf
	 * .entities.properties.ICanMove)
	 */
	@Override
	public void follow(final ICanMove target) {
		if (mCurrentDestination.isPresent()) {
			mCurrentDestination = Optional.empty();
		}
		mFollowTarget = Optional.of(target);
		mIsMoving = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#isMoving()
	 */
	@Override
	public boolean isMoving() {
		return mIsMoving;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveTo(float, float)
	 */
	@Override
	public void moveTo(final float x, final float y) {
		if (mFollowTarget.isPresent()) {
			mFollowTarget = Optional.empty();
		}
		mCurrentDestination = Optional.of(new Point2D.Float(x, y));
		mIsMoving = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#stopMovement()
	 */
	@Override
	public void stopMovement() {
		mIsMoving = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {
		// Nothing to update if currently not moving
		if (!mIsMoving) {
			return;
		}
		
		if (mFollowTarget.isPresent()) {
			ICanMove target = mFollowTarget.get();
			mCurrentDestination = Optional.of(new Point2D.Float(target.getRelativeX(), target.getRelativeY()));
		}
		
		if (mCurrentDestination.isPresent()) {
			Point2D position = new Point2D.Float(mParent.getRelativeX(), mParent.getRelativeY());
			Point2D destination = mCurrentDestination.get();
			
			// Move to the destination
			mParent.setRelativeX((float) destination.getX());
			mParent.setRelativeY((float) destination.getY());
			
			// Arrival at specific destination
			if (position.distance(destination) <= ARRIVAL_RADIUS) {
				stopMovement();
			}
		}
	}

	@Override
	public void roam(final GameTime gameTime) {
		
	}
}
