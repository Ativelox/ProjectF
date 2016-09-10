package de.cormag.projectf.logic.movement;

import de.cormag.projectf.entities.properties.ICanMove;

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
	private final ICanMove mParent;

	/**
	 * Creates a new behavior which belongs to the given parent.
	 * 
	 * @param parent
	 *            Parent the behavior belongs to
	 */
	public TeleportMoveBehavior(final ICanMove parent) {
		mParent = parent;
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
		moveTo(target.getRelativeX(), target.getRelativeY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#isMoving()
	 */
	@Override
	public boolean isMoving() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#moveTo(float, float)
	 */
	@Override
	public void moveTo(final float x, final float y) {
		mParent.setRelativeX(x);
		mParent.setRelativeY(y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.movement.IMoveBehavior#stopMovement()
	 */
	@Override
	public void stopMovement() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update() {

	}

}
