package de.cormag.projectf.logic.movement;

import java.awt.geom.Point2D;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.entities.properties.IHaveAnimations;
import de.cormag.projectf.utils.Utils;
import de.cormag.projectf.utils.time.GameTime;

/**
 * 
 * The default MoveBehavior which takes collision into account and can follow properly
 * 
 * @author Ativelox
 */

public class MoveBehavior implements IMoveBehavior{
	
	/**
	 * The number of update calls this entity will predict his movement for, if any collision 
	 * were to occur in the predicted update calls, the object won't move.
	 */
	private final static int PRECAUTIOUS_COLLISION_PADDING = 10;
	/**
	 * If present, the target to follow.
	 */
	private Optional<ICanMove> mFollowTarget;
	/**
	 * Parent object this behavior belongs to.
	 */
	private final ICanMove mParent;
	
	/**
	 * Parent object this behavior belongs cast to entity.
	 */
	private final Creature mParentAsCreature;
	/**
	 * If present, the current destination as point to move to.
	 */
	private Optional<Point2D> mCurrentDestination;
	/**
	 * Whether the object is currently moving.
	 */
	private boolean mIsMoving;
	
	private double elapsedTime;

	/**
	 * Creates a new behavior which belongs to the given parent.
	 * 
	 * @param parent
	 *            Parent the behavior belongs to
	 */
	public MoveBehavior(final ICanMove parent) {
		mParent = parent;
		mParentAsCreature = (Creature) parent;
		mFollowTarget = Optional.empty();
		mCurrentDestination = Optional.empty();
		
		elapsedTime = 0.8;
		
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
		
		//got a target to follow or a position to move to
		if (mCurrentDestination.isPresent()) {
			Point2D position = new Point2D.Float(mParent.getRelativeX(), mParent.getRelativeY());
			Point2D destination = mCurrentDestination.get();

			Point2D newPos = Utils.normalizeVector(position, destination);
			Point2D movementPerCall = new Point2D.Double(newPos.getX(), newPos.getY());
			
			if(mParent instanceof IHaveAnimations){
				
				if(movementPerCall.getX() > 0){
					((IHaveAnimations) mParent).setHorizontalDirection(1);
					
				}
				if(movementPerCall.getX() < 0){
					((IHaveAnimations) mParent).setHorizontalDirection(-1);
					
				}
				if(movementPerCall.getY() > 0){
					((IHaveAnimations) mParent).setVerticalDirection(1);
					
				}
				if(movementPerCall.getY() < 0){
					((IHaveAnimations) mParent).setVerticalDirection(-1);
					
				}
			}
			
			newPos.setLocation(position.getX() + (newPos.getX() * (mParent.getMovementSpeed() * gameTime.getElapsedTime().get(ChronoUnit.SECONDS))),
					position.getY() + (newPos.getY() * (mParent.getMovementSpeed() * gameTime.getElapsedTime().get(ChronoUnit.SECONDS))));

			
			if (!mParentAsCreature.checkEntityCollisions(movementPerCall.getX() * PRECAUTIOUS_COLLISION_PADDING, 0f)){
				mParent.setRelativeX((float) newPos.getX());

			}
			if (!mParentAsCreature.checkEntityCollisions(0f, movementPerCall.getY() * PRECAUTIOUS_COLLISION_PADDING)){
				mParent.setRelativeY((float) newPos.getY());

			}
	
		}
	}
	
	@Override
	public void roam(final GameTime gameTime) {
		if(mFollowTarget.isPresent()){
			mFollowTarget = Optional.empty();
		}
		
		elapsedTime += gameTime.getElapsedTime().get(ChronoUnit.SECONDS);
		
		float newPosX = mParent.getRelativeX();
		float newPosY = mParent.getRelativeY();
		
		if(elapsedTime >= 1){
			
			if(Math.round(Math.random()) == 0){
				//50% chance to move horizontal
				short horizontalDirection = (short) Math.round(Math.random() * 2 - 1);
				newPosX = mParent.getRelativeX() * mParent.getMovementSpeed() * horizontalDirection;
				
			}else{
				//50% chance to move vertical
				short verticalDirection = (short) Math.round(Math.random() * 2 - 1);
				newPosY = mParent.getRelativeY() * mParent.getMovementSpeed() * verticalDirection;
		
			}
			
			mCurrentDestination = Optional.of(new Point2D.Float(newPosX, newPosY));
			
			mIsMoving = true;
			elapsedTime = 0;
		}

	}
	

}

