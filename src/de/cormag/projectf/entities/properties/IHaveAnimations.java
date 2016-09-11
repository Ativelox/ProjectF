package de.cormag.projectf.entities.properties;

import java.awt.image.BufferedImage;

/**
 * Basic interface used for objects that have animations
 * 
 * @author Ativelox
 *
 */

public interface IHaveAnimations {
	/**
	 * This method returns the image which should be drawn considering the animations given. It also sets the steady animation of the object.
	 * @see de.projectf.cormag.entities.properties.IHaveAnimations#setSteadyAnimation(BufferedImage imageToSet)
	 * @see de.projectf.cormag.gfx.Animation#Animation(int speed, BufferedImage[] frames)
	 * 
	 * @param steadyLeft The picture which is used when the target is currently not animated, facing to the left
	 * @param steadyRight The picture which is used when the target is currently not animated, facing to the right
	 * @param steadyUp The picture which is used when the target is currently not animated, facing upwards
	 * @param steadyDown The picture which is used when the target is currently not animated, facing downwards
	 * @return the image which should be drawn to the screen
	 */
	public BufferedImage getCurrentAnimationFrame(BufferedImage steadyLeft, BufferedImage steadyRight, BufferedImage steadyUp,
			BufferedImage steadyDown);
	
	/**
	 * Gets the steady animation of an object. The steady animation is the picture which is used
	 * instead of an animation, if the object is idle
	 * 
	 * @return the steady animation of an object
	 */
	public BufferedImage getSteadyAnimation();
	
	/**
	 * Sets the steady animation of an object. The steady animation is the picture which is used
	 * instead of an animation, if the object is idle
	 * 
	 * @param imageToSet specifies the image which should be used as steady animation
	 */
	public void setSteadyAnimation(BufferedImage imageToSet);
}
