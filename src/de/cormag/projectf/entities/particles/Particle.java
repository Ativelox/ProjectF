package de.cormag.projectf.entities.particles;

import java.awt.Rectangle;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.properties.ISpatial;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Class for particle effect entities. Those entities have no collision and are
 * purely cosmetic.
 * 
 * @author Zabuza
 *
 */
public abstract class Particle extends Entity implements ISpatial, ICanMove {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getOldRelativeX()
	 */
	@Override
	public float getOldRelativeX() {
		return mOldRelativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getOldRelativeY()
	 */
	@Override
	public float getOldRelativeY() {
		return mOldRelativeY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getRelativeX()
	 */
	@Override
	public float getRelativeX() {
		return mRelativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getRelativeY()
	 */
	@Override
	public float getRelativeY() {
		return mRelativeY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cormag.projectf.entities.properties.ISpatial#setOldRelativeX(float)
	 */
	@Override
	public void setOldRelativeX(float oldRelativeX) {
		this.mOldRelativeX = oldRelativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cormag.projectf.entities.properties.ISpatial#setOldRelativeY(float)
	 */
	@Override
	public void setOldRelativeY(float oldRelativeY) {
		this.mOldRelativeY = oldRelativeY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#setRelativeX(float)
	 */
	@Override
	public void setRelativeX(float relativeX) {
		this.mRelativeX = relativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#setRelativeY(float)
	 */
	@Override
	public void setRelativeY(float relativeY) {
		this.mRelativeY = relativeY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.Entity#update()
	 */
	@Override
	public void update(final GameTime gameTime) {
		setOldRelativeX(getRelativeX());
		setOldRelativeY(getRelativeY());

		super.update(gameTime);

		// Translate relative movement to absolute
		setX(getRelativeX() - xOffset);
		setY(getRelativeY() - yOffset);

		hasMoved = getOldRelativeX() != getRelativeX() || getOldRelativeY() != getRelativeY();
	}
}
