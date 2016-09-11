package de.cormag.projectf.logic.movement;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.utils.time.GameTime;

/**
 * 
 * The default MoveBehavior which takes collision into account and can follow properly
 * 
 * @author Ativelox
 */

public class MoveBehavior implements IMoveBehavior{

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
		
		elapsedTime = 0;
		
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
			
			Point2D positionCenter = new Point2D.Double(position.getX() + ((Entity)mParent).getWidth() / 2, 
														position.getY() + ((Entity)mParent).getHeight() / 2);
			
			Point2D destinationCenter = new Point2D.Double(destination.getX() + ((Entity)mFollowTarget.get()).getWidth() / 2,
														   destination.getY() + ((Entity)mFollowTarget.get()).getHeight() / 2);
			
			if (destinationCenter.getX() > positionCenter.getX() - 5) {
				stopHorizontalMovement();

			} else if (destinationCenter.getX() < positionCenter.getX()) {
				moveLeft(true);

			}
			if (destinationCenter.getX() > positionCenter.getX()) {
				moveRight(true);

			}
			if (destinationCenter.getY() > positionCenter.getY() - 5) {
				stopVerticalMovement();
				

			} else if (destinationCenter.getY() < positionCenter.getY()) {
				moveUp(true);
				

			}
			if (destinationCenter.getY() > positionCenter.getY()) {
				moveDown(true);

			}	
			move();
		}
	
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#move();
	 */
	@Override
	public void move() {
		if (!mParentAsCreature.checkEntityCollisions(mParent.getXMove(), 0f))
			moveX();
		if (!mParentAsCreature.checkEntityCollisions(0f, mParent.getYMove()))
			moveY();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveX();
	 */
	@Override
	public void moveX() {
		
		Rectangle bounds = mParentAsCreature.getBounds();
		
		if (mParent.getXMove() > 0) {// Moving right
			int tx = (int) (mParent.getRelativeX() + mParent.getXMove() + bounds.x + bounds.width) / Tile.TILEWIDTH;

			if (!mParentAsCreature.collisionWithTile(tx, (int) (mParent.getRelativeY() + bounds.y) / Tile.TILEHEIGHT)
					&& !mParentAsCreature.collisionWithTile(tx, (int) (mParent.getRelativeY() + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				mParent.setRelativeX(mParent.getRelativeX() + mParent.getXMove());
			} else {
				mParent.setRelativeX(tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1);
			}

		} else if (mParent.getXMove() < 0) {// Moving left
			int tx = (int) (mParent.getRelativeX() + mParent.getXMove() + bounds.x) / Tile.TILEWIDTH;

			if (!mParentAsCreature.collisionWithTile(tx, (int) (mParent.getRelativeY() + bounds.y) / Tile.TILEHEIGHT)
					&& !mParentAsCreature.collisionWithTile(tx, (int) (mParent.getRelativeY() + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				mParent.setRelativeX(mParent.getRelativeX() + mParent.getXMove());
			} else {
				mParent.setRelativeX(tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x);
			}

		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveY();
	 */
	@Override
	public void moveY() {
		
		Rectangle bounds = mParentAsCreature.getBounds();
		
		if (mParent.getYMove() < 0) {// Up
			int ty = (int) (mParent.getRelativeY() + mParent.getYMove() + bounds.y) / Tile.TILEHEIGHT;

			if (!mParentAsCreature.collisionWithTile((int) (mParent.getRelativeX() + bounds.x) / Tile.TILEWIDTH, ty)
					&& !mParentAsCreature.collisionWithTile((int) (mParent.getRelativeX() + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				mParent.setRelativeY(mParent.getRelativeY() + mParent.getYMove());
			} else {
				mParent.setRelativeY(ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y);
			}

		} else if (mParent.getYMove() > 0) {// Down
			int ty = (int) (mParent.getRelativeY() + mParent.getYMove() + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (!mParentAsCreature.collisionWithTile((int) (mParent.getRelativeX() + bounds.x) / Tile.TILEWIDTH, ty)
					&& !mParentAsCreature.collisionWithTile((int) (mParent.getRelativeX() + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				mParent.setRelativeY(mParent.getRelativeY() + mParent.getYMove());
			} else {
				mParent.setRelativeY(ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1);
			}

		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveUp(boolean isMoving);
	 */
	@Override
	public void moveUp(boolean isMoving){
		if(isMoving){
			mParent.setYMove(-mParent.getMovementSpeed());
		}else if(mParent.getYMove() > 0){
			return;
		}else{
			mParent.setYMove(0f);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveDown(boolean isMoving);
	 */
	@Override
	public void moveDown(boolean isMoving){
		if(isMoving){
			mParent.setYMove(mParent.getMovementSpeed());
		}else if(mParent.getYMove() < 0){
			return;
		}else{
			mParent.setYMove(0f);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveLeft(boolean isMoving);
	 */
	@Override
	public void moveLeft(boolean isMoving){
		if(isMoving){
			mParent.setXMove(-mParent.getMovementSpeed());
		}else if(mParent.getXMove() > 0){
			return;
		}else{
			mParent.setXMove(0f);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveRight(boolean isMoving);
	 */
	@Override
	public void moveRight(boolean isMoving){
		if(isMoving){
			mParent.setXMove(mParent.getMovementSpeed());
		}else if(mParent.getXMove() < 0){
			return;
		}else{
			mParent.setXMove(0f);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#stopHorizontalMovement();
	 */
	@Override
	public void stopHorizontalMovement(){
		mParent.setXMove(0f);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#stopVerticalMovement();
	 */
	@Override
	public void stopVerticalMovement(){
		mParent.setYMove(0f);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#runUp(boolean isMoving);
	 */
	@Override
	public void runUp(boolean isMoving) {
		if(isMoving){
			mParent.setYMove(-mParent.getRunningSpeed());
		}else if(mParent.getYMove() > 0){
			return;
		}else{
			mParent.setYMove(0f);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#runDown(boolean isMoving);
	 */
	@Override
	public void runDown(boolean isMoving) {
		if(isMoving){
			mParent.setYMove(mParent.getRunningSpeed());
		}else if(mParent.getYMove() < 0){
			return;
		}else{
			mParent.setYMove(0f);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#runRight(boolean isMoving);
	 */
	@Override
	public void runRight(boolean isMoving) {
		if(isMoving){
			mParent.setXMove(mParent.getRunningSpeed());
		}else if(mParent.getXMove() < 0){
			return;
		}else{
			mParent.setXMove(0f);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#runLeft(boolean isMoving);
	 */
	@Override
	public void runLeft(boolean isMoving) {
		if(isMoving){
			mParent.setXMove(-mParent.getRunningSpeed());
		}else if(mParent.getXMove() > 0){
			return;
		}else{
			mParent.setXMove(0f);
		}
		
	}

	@Override
	public void searchForTargets(final GameTime gameTime) {
		
		elapsedTime += gameTime.getElapsedTime().get(ChronoUnit.SECONDS);
		
		if(elapsedTime >= 1){
			if (Math.round(Math.random()) == 0) {
				mParent.setYMove(0f);
				mParent.setXMove(((int) Math.round((Math.random() * 2) - 1)) * mParent.getMovementSpeed());
	
			}else {
				mParent.setXMove(0f);
				mParent.setYMove(((int) Math.round((Math.random() * 2) - 1)) * mParent.getMovementSpeed());
	
			}
			
			elapsedTime = 0;
		}
		
		move();
	}
}

