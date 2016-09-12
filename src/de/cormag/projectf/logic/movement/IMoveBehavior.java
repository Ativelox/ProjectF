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
	 * Follows the given target indefinitely.
	 * 
	 * @param target
	 *            A move-able object to follow.
	 */
	public void follow(final ICanMove target);
	
	/**
	 * Makes the object randomly move around.
	 */
	public void roam(final GameTime gameTime);

	/**
	 * Stops the movement of the object.
	 */
	public void stopMovement();

}
