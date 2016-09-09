package de.cormag.projectf.entities.properties;

/**
 * Interface for moveable objects.
 * 
 * @author Zabuza
 *
 */
public interface IMoveable extends ISpatial {
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
