package de.cormag.projectf.entities.properties;

/**
 * Interface for objects that have a physical presence in the game world.
 * 
 * @author Zabuza
 *
 */
public interface ISpatial {
	/**
	 * Gets the x-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @return the oldRelativeX The x-coordinate relative to the camera, i.e.
	 *         the world position, of the last tick.
	 */
	public float getOldRelativeX();

	/**
	 * Gets the y-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @return The y-coordinate relative to the camera, i.e. the world position,
	 *         of the last tick.
	 */
	public float getOldRelativeY();

	/**
	 * Gets the current x-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @return The current x-coordinate relative to the camera, i.e. the world
	 *         position.
	 */
	public float getRelativeX();

	/**
	 * Gets the current y-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @return The current y-coordinate relative to the camera, i.e. the world
	 *         position.
	 */
	public float getRelativeY();

	/**
	 * Sets the x-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @param oldRelativeX
	 *            The x-coordinate relative to the camera, i.e. the world
	 *            position, of the last tick.
	 */
	public void setOldRelativeX(float oldRelativeX);

	/**
	 * Sets the y-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @param oldRelativeY
	 *            The y-coordinate relative to the camera, i.e. the world
	 *            position, of the last tick.
	 */
	public void setOldRelativeY(float oldRelativeY);

	/**
	 * Sets the current x-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @param relativeX
	 *            The current x-coordinate relative to the camera, i.e. the
	 *            world position.
	 */
	public void setRelativeX(float relativeX);

	/**
	 * Sets the current y-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @param relativeY
	 *            The current y-coordinate relative to the camera, i.e. the
	 *            world position.
	 */
	public void setRelativeY(float relativeY);
}
