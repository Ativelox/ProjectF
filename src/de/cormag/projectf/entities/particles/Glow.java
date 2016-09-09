package de.cormag.projectf.entities.particles;

import java.awt.Color;
import java.awt.Graphics;

import de.cormag.projectf.main.Handler;

/**
 * Small glowing particle which slowly moves around the scene for ambiance.
 * 
 * @author Zabuza
 *
 */
public final class Glow extends Particle {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The width and height of this entity.
	 */
	private static final int SIZE = 20;

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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.Entity#render(java.awt.Graphics)
	 */
	@Override
	public void render(final Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval((int) getX(), (int) getY(), getWidth(), getHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.Entity#tick()
	 */
	@Override
	public void update() {
		super.update();
	}
}
