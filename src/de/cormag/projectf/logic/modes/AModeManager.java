package de.cormag.projectf.logic.modes;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.properties.IUpdateable;

/**
 * Manages a modes. Transitions between modes when necessary and forwards events
 * to the currently active mode.
 * 
 * @author Zabuza
 *
 */
public abstract class AModeManager implements IUpdateable {

	/**
	 * The entity for which this object manages modes for.
	 */
	private final Entity mParent;

	/**
	 * Creates a new mode manager which belongs to a given entity.
	 * 
	 * @param parent
	 *            Entity for which this object manages modes for
	 */
	public AModeManager(final Entity parent) {
		mParent = parent;
	}

	/**
	 * Gets the control object to the current active mode. Note that there must
	 * always be an active mode.
	 * 
	 * @return The control object to the current active mode.
	 */
	protected abstract IModeControl getCurrentModeControl();

	/**
	 * Gets the entity for which this object manages modes for.
	 * 
	 * @return The entity for which this object manages modes for
	 */
	protected Entity getParent() {
		return mParent;
	}
}
