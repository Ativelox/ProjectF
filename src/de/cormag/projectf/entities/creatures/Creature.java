package de.cormag.projectf.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.entities.properties.IHaveAnimations;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public abstract class Creature extends Entity implements ICanMove, IHaveAnimations {

	private static final long serialVersionUID = 1L;

	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	protected float speed;
	protected float runningSpeed;
	protected int verticalDirection, horizontalDirection;

	protected transient Animation walkingAnimDown, walkingAnimUp, walkingAnimLeft, walkingAnimRight, runningAnimDown,
			runningAnimUp, runningAnimLeft, runningAnimRight;

	protected boolean sprinting;

	transient protected BufferedImage steadyAnimation;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		sprinting = false;
		runningSpeed = Creature.DEFAULT_SPEED * 2;

		verticalDirection = 0;
		horizontalDirection = 0;

		getBounds().x = width / 3;
		getBounds().y = height / 3;
		getBounds().width = width / 3;
		getBounds().height = 2 * height / 3;
	}

	public void render(Graphics g, final GameTime gameTime, BufferedImage image) {
		g.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
		super.render(g, gameTime);

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

	}

	@Override
	public BufferedImage getCurrentAnimationFrame(BufferedImage steadyLeft, BufferedImage steadyRight,
			BufferedImage steadyUp, BufferedImage steadyDown) {

		if (sprinting) {

			if (horizontalDirection == -1) {
				setSteadyAnimation(steadyLeft);
				return runningAnimLeft.getCurrentFrame();

			} else if (horizontalDirection == 1) {
				setSteadyAnimation(steadyRight);
				return runningAnimRight.getCurrentFrame();

			} else if (verticalDirection == -1) {
				setSteadyAnimation(steadyUp);
				return runningAnimUp.getCurrentFrame();

			} else if (verticalDirection == 1) {
				setSteadyAnimation(steadyDown);
				return runningAnimDown.getCurrentFrame();

			} else {
				return getSteadyAnimation();
			}

		} else {

			if (horizontalDirection == -1) {
				setSteadyAnimation(steadyLeft);
				return walkingAnimLeft.getCurrentFrame();

			} else if (horizontalDirection == 1) {
				setSteadyAnimation(steadyRight);
				return walkingAnimRight.getCurrentFrame();

			} else if (verticalDirection == -1) {
				setSteadyAnimation(steadyUp);
				return walkingAnimUp.getCurrentFrame();

			} else if (verticalDirection == 1) {
				setSteadyAnimation(steadyDown);
				return walkingAnimDown.getCurrentFrame();

			} else {
				return getSteadyAnimation();
			}
		}

	}

	@Override
	public void setSteadyAnimation(BufferedImage imageToSet) {
		steadyAnimation = imageToSet;

	}

	@Override
	public BufferedImage getSteadyAnimation() {
		return steadyAnimation;

	}

	public boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	@Override
	public float getMovementSpeed() {
		return speed;
	}

	@Override
	public void setMovementSpeed(float amount) {
		this.speed = amount;

	}

	@Override
	public float getRunningSpeed() {
		return runningSpeed;
	}

	@Override
	public void setRunningSpeed(float amount) {
		runningSpeed = amount;

	}

	@Override
	public int getHorizontalDirection() {
		return horizontalDirection;
	}

	@Override
	public void setHorizontalDirection(int direction) {
		horizontalDirection = direction;

	}

	@Override
	public int getVerticalDirection() {
		return verticalDirection;
	}

	@Override
	public void setVerticalDirection(int direction) {
		verticalDirection = direction;

	}

	@Override
	public boolean isSprinting() {
		return sprinting;
	}

}