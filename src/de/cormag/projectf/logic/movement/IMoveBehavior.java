package de.cormag.projectf.logic.movement;

import de.cormag.projectf.entities.properties.IUpdateable;

/**
 * Interface for objects that define the logic how to move.
 * 
 * @author Zabuza
 *
 */
public interface IMoveBehavior extends IUpdateable {
	/**
	 * Moves the object to the given position.
	 * 
	 * @param x
	 *            X-coordinate absolute in the world to move to
	 * @param y
	 *            Y-coordinate absolute in the world to move to
	 */
	public void moveTo(final float x, final float y);
}
