package de.cormag.projectf.entities.properties;

import java.awt.Graphics;

/**
 * Interface for renderable objects which also supports layers.
 * 
 * @author Zabuza
 *
 */
public interface IRenderable {
	/**
	 * Layer for objects that stand in the back.
	 */
	public static int BACK_LAYER = -5;
	/**
	 * The biggest layer for objects.
	 */
	public static int BIGGEST_LAYER = 20;
	/**
	 * The default layer for objects.
	 */
	public static int DEFAULT_LAYER = 0;
	/**
	 * Layer for objects that stand in the front.
	 */
	public static int FRONT_LAYER = 5;
	/**
	 * Layer for HUD elements.
	 */
	public static int HUD_LAYER = 16;
	/**
	 * Layer for particles and similar effects.
	 */
	public static int PARTICLE_LAYER = 12;
	/**
	 * The smallest layer for objects.
	 */
	public static int SMALLEST_LAYER = -20;

	/**
	 * Gets the layer onto which this object should be rendered. This determines
	 * the order of objects being rendered. Objects with a lower layer get
	 * rendered behind those on higher layers.
	 * 
	 * @return The layer onto which this object should be rendered. Objects with
	 *         a lower layer get rendered behind those on higher layers.
	 */
	public int getLayer();

	/**
	 * Renders this object on the screen using the given graphics object.
	 * 
	 * @param g
	 *            Graphics to render with
	 */
	public void render(final Graphics g);
}
