package de.cormag.projectf.logic.modes;

import de.cormag.projectf.entities.properties.IUpdateable;

/**
 * Controls the mode of a parent object.
 * 
 * @author Zabuza
 *
 */
public interface IModeControl extends IUpdateable {
	/**
	 * Activates the mode.
	 */
	public void activate();

	/**
	 * Terminates the mode.
	 */
	public void terminate();
}
