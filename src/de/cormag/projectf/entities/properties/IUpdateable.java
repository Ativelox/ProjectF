package de.cormag.projectf.entities.properties;

import de.cormag.projectf.utils.time.GameTime;

/**
 * Interface for updateable objects.
 * 
 * @author Zabuza
 *
 */
public interface IUpdateable {
	/**
	 * Updates the the logic of this object.
	 * 
	 * @param gameTime
	 *            Object which provides various time methods and perspectives
	 *            for the current update cycle.
	 */
	public void update(final GameTime gameTime);
}
