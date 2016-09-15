package de.cormag.projectf.entities.particles;

import java.awt.Color;
import java.awt.Graphics;

import de.cormag.projectf.logic.modes.AModeManager;
import de.cormag.projectf.logic.modes.particles.EParticleMode;
import de.cormag.projectf.logic.modes.particles.ParticleModeManager;
import de.cormag.projectf.logic.modes.particles.PassiveWobbleControl;
import de.cormag.projectf.logic.movement.IMoveBehavior;
import de.cormag.projectf.logic.movement.TeleportMoveBehavior;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Small glowing particle which slowly moves around the scene for ambiance.
 * 
 * @author Zabuza
 *
 */
public final class Glow extends Particle {

	/**
	 * The render color for the inner part of the oval.
	 */
	private final static Color INNER_COLOR = new Color(180, 0, 0, 100);

	/**
	 * The render color for the outline of the oval.
	 */
	private final static Color OUTLINE_COLOR = new Color(255, 0, 0, 200);

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The width and height of this entity.
	 */
	private static final int SIZE = 10;
	/**
	 * Object which manages the modes of this particle.
	 */
	private final AModeManager mModeManager;

	/**
	 * Creates a new glow particle at the given coordinates.
	 * 
	 * @param handler
	 *            Handler to the game
	 * @param x
	 *            X-coordinate to create this object at
	 * @param y
	 *            Y-coordinate to create this object at
	 */
	public Glow(final Handler handler, final float x, final float y) {
		super(handler, x, y, SIZE, SIZE);

		// Behavior, control and manager creation
		IMoveBehavior moveBehavior = new TeleportMoveBehavior(this);
		PassiveWobbleControl passiveWobbleControl = new PassiveWobbleControl(this, moveBehavior);
		mModeManager = new ParticleModeManager(this, passiveWobbleControl, EParticleMode.PASSIVE_WOBBLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.Entity#render(java.awt.Graphics)
	 */

	@Override
	public void render(final Graphics g, final GameTime gameTime) {
		g.setColor(INNER_COLOR);
		g.fillOval((int) getX(), (int) getY(), getWidth(), getHeight());
		g.setColor(OUTLINE_COLOR);
		g.drawOval((int) getX(), (int) getY(), getWidth(), getHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.Entity#tick()
	 */
	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);
		mModeManager.update(gameTime);
	}

	@Override
	public float getMovementSpeed() {
		return 0;
	}

	@Override
	public void setMovementSpeed(float amount) {

	}

	@Override
	public float getRunningSpeed() {
		return 0;
	}

	@Override
	public void setRunningSpeed(float amount) {

	}

	@Override
	public boolean isSprinting() {
		return false;
	}
}
