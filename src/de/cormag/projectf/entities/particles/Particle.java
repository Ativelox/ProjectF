package de.cormag.projectf.entities.particles;

import java.awt.Rectangle;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.main.Handler;

/**
 * Class for particle effect entities. Those entities have no collision and are
 * purely cosmetic.
 * 
 * @author Zabuza
 *
 */
public abstract class Particle extends Entity {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Holds the x-coordinate relative to the camera, i.e. the world position,
	 * of the last tick.
	 */
	private float mOldRelativeX;
	/**
	 * Holds the y-coordinate relative to the camera, i.e. the world position,
	 * of the last tick.
	 */
	private float mOldRelativeY;
	/**
	 * Holds the current x-coordinate relative to the camera, i.e. the world
	 * position.
	 */
	private float mRelativeX;
	/**
	 * Holds the current y-coordinate relative to the camera, i.e. the world
	 * position.
	 */
	private float mRelativeY;

	public Particle(final Handler handler, final float x, final float y, final int width, final int height) {
		super(handler, x, y, width, height);

		setBounds(new Rectangle(0, 0, 0, 0));
		setOldRelativeX(x);
		setOldRelativeY(y);
		setRelativeX(x);
		setRelativeY(y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.Entity#checkEntityCollisions(float,
	 * float)
	 */
	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.Entity#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.PARTICLE_LAYER;
	}

	/**
	 * Gets the x-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @return the oldRelativeX The x-coordinate relative to the camera, i.e.
	 *         the world position, of the last tick.
	 */
	public float getOldRelativeX() {
		return mOldRelativeX;
	}

	/**
	 * Gets the y-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @return The y-coordinate relative to the camera, i.e. the world position,
	 *         of the last tick.
	 */
	public float getOldRelativeY() {
		return mOldRelativeY;
	}

	/**
	 * Gets the current x-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @return The current x-coordinate relative to the camera, i.e. the world
	 *         position.
	 */
	public float getRelativeX() {
		return mRelativeX;
	}

	/**
	 * Gets the current y-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @return The current y-coordinate relative to the camera, i.e. the world
	 *         position.
	 */
	public float getRelativeY() {
		return mRelativeY;
	}

	/**
	 * Sets the x-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @param oldRelativeX
	 *            The x-coordinate relative to the camera, i.e. the world
	 *            position, of the last tick.
	 */
	protected void setOldRelativeX(float oldRelativeX) {
		this.mOldRelativeX = oldRelativeX;
	}

	/**
	 * Sets the y-coordinate relative to the camera, i.e. the world position, of
	 * the last tick.
	 * 
	 * @param oldRelativeY
	 *            The y-coordinate relative to the camera, i.e. the world
	 *            position, of the last tick.
	 */
	protected void setOldRelativeY(float oldRelativeY) {
		this.mOldRelativeY = oldRelativeY;
	}

	/**
	 * Sets the current x-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @param relativeX
	 *            The current x-coordinate relative to the camera, i.e. the
	 *            world position.
	 */
	protected void setRelativeX(float relativeX) {
		this.mRelativeX = relativeX;
	}

	/**
	 * Sets the current y-coordinate relative to the camera, i.e. the world
	 * position.
	 * 
	 * @param relativeY
	 *            The current y-coordinate relative to the camera, i.e. the
	 *            world position.
	 */
	protected void setRelativeY(float relativeY) {
		this.mRelativeY = relativeY;
	}
}
