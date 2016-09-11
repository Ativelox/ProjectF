package de.cormag.projectf.logic.movement;

import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.entities.properties.IUpdateable;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Interface for objects that define the logic how to move.
 * 
 * @author Zabuza
 *
 */
public interface IMoveBehavior extends IUpdateable {
	/**
	 * Follows the given target indefinitely.
	 * 
	 * @param target
	 *            A move-able object to follow.
	 */
	public void follow(final ICanMove target);

	/**
	 * Whether the object is currently moving.
	 * 
	 * @return <tt>True</tt> if the object is currently moving, <tt>false</tt>
	 *         else.
	 */
	public boolean isMoving();

	/**
	 * Moves the object to the given position.
	 * 
	 * @param x
	 *            X-coordinate absolute in the world to move to
	 * @param y
	 *            Y-coordinate absolute in the world to move to
	 */
	public void moveTo(final float x, final float y);

	/**
	 * Stops the movement of the object.
	 */
	public void stopMovement();

	/**
	 * Calls the moveX and moveY methods. Moves the object as long as there won't occur any collision with other objects.
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveX()
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveY()
	 */
	public void move();
	
	/**
	 * Moves the object in horizontal direction. Aligns this object to objects which can not be passed through.
	 */
	public void moveX();
	
	/**
	 * Moves the object in vertical direction. Aligns this object to objects which can not be passed through.
	 */
	public void moveY();
	
	/**
	 * Moves the object upwards by the movement speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will move up </br><tt>false:</tt> the movement upwards will be blocked
	 */
	public void moveUp(boolean isMoving);
	
	/**
	 * Moves the object downwards by the movement speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will move down </br><tt>false:</tt> the movement downwards will be blocked
	 */
	public void moveDown(boolean isMoving);
	
	/**
	 * Moves the object to the right by the movement speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will move right </br><tt>false:</tt> the movement to the right will be blocked
	 */
	public void moveRight(boolean isMoving);
	
	/**
	 * Moves the object to the left by the movement speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will move left </br><tt>false:</tt> the movement to the left will be blocked
	 */
	public void moveLeft(boolean isMoving);
	
	/**
	 * Makes the object run upwards by the running speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will run upwards </br><tt>false:</tt> the running motion upwards will be blocked
	 */
	public void runUp(boolean isMoving);
	
	/**
	 * Makes the object run downwards by the running speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will run downwards </br><tt>false:</tt> the running motion downwards will be blocked
	 */
	public void runDown(boolean isMoving);
	
	/**
	 * Makes the object run to the right by the running speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will run to the right </br><tt>false:</tt> the running motion to the right will be blocked
	 */
	public void runRight(boolean isMoving);

	/**
	 * Makes the object run to the left by the running speed the object possesses
	 * @param isMoving </br><tt>true:</tt> the target will run to the left </br><tt>false:</tt> the running motion to the left will be blocked
	 */
	public void runLeft(boolean isMoving);
	
	/**
	 * Stops the horizontal movement of the object for as long as xMove doesn't get set
	 * @see de.cormag.projectf.entities.properties.IHaveSpeed#setXMove(float)
	 */
	public void stopHorizontalMovement();
	
	/**
	 * Stops the vertical movement of the object for as long as yMove doesn't get set
	 * @see de.cormag.projectf.entities.properties.IHaveSpeed#setYMove(float)
	 */
	public void stopVerticalMovement();
	
	/**
	 * Makes the object randomly move around so it can actively search for targets
	 */
	public void searchForTargets(final GameTime gameTime);
}
