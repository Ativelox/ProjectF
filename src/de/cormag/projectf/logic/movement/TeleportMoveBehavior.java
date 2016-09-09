package de.cormag.projectf.logic.movement;

import de.cormag.projectf.entities.properties.IMoveable;

/**
 * Behavior which resolves movement by direct teleporting.
 * 
 * @author Zabuza
 *
 */
public final class TeleportMoveBehavior implements IMoveBehavior {

	/**
	 * Parent object this behavior belongs to.
	 */
	private final IMoveable mParent;

	/**
	 * Creates a new behavior which belongs to the given parent.
	 * 
	 * @param parent
	 *            Parent the behavior belongs to
	 */
	public TeleportMoveBehavior(final IMoveable parent) {
		mParent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveTo(float, float)
	 */
	@Override
	public void moveTo(final float x, final float y) {
		mParent.setRelativeX(x);
		mParent.setRelativeX(y);
	}

}
