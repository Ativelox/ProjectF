package de.cormag.projectf.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public abstract class Creature extends Entity implements ICanMove{

	private static final long serialVersionUID = 1L;

	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	protected float speed;
	protected float runningSpeed;
	protected float xMove, yMove;

	protected transient Animation walkingAnimDown, walkingAnimUp, walkingAnimLeft, walkingAnimRight, runningAnimDown,
			runningAnimUp, runningAnimLeft, runningAnimRight;

	protected boolean sprinting;

	transient protected BufferedImage steadyAnimation;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		sprinting = false;
		runningSpeed = Creature.DEFAULT_SPEED * 2;
		xMove = 0;
		yMove = 0;

		getBounds().x = width / 3;
		getBounds().y = height / 3;
		getBounds().width = width / 3;
		getBounds().height = 2 * height / 3;
	}
	
	public void render(Graphics g, BufferedImage image){
		g.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null); 
		super.render(g);

		
	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMove))
			moveY();
	}

	public void moveX() {
		if (xMove > 0) {// Moving right
			int tx = (int) (getRelativeX() + xMove + getBounds().x + getBounds().width) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (getRelativeY() + getBounds().y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (getRelativeY() + getBounds().y + getBounds().height) / Tile.TILEHEIGHT)) {
				setRelativeX(getRelativeX() + xMove);
			} else {
				setRelativeX(tx * Tile.TILEWIDTH - getBounds().x - getBounds().width - 1);
			}

		} else if (xMove < 0) {// Moving left
			int tx = (int) (getRelativeX() + xMove + getBounds().x) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (getRelativeY() + getBounds().y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (getRelativeY() + getBounds().y + getBounds().height) / Tile.TILEHEIGHT)) {
				setRelativeX(getRelativeX() + xMove);
			} else {
				setRelativeX(tx * Tile.TILEWIDTH + Tile.TILEWIDTH - getBounds().x);
			}

		}
	}

	public void moveY() {
		if (yMove < 0) {// Up
			int ty = (int) (getRelativeY() + yMove + getBounds().y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (getRelativeX() + getBounds().x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (getRelativeX() + getBounds().x + getBounds().width) / Tile.TILEWIDTH, ty)) {
				setRelativeY(getRelativeY() + yMove);
			} else {
				setRelativeY(ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - getBounds().y);
			}

		} else if (yMove > 0) {// Down
			int ty = (int) (getRelativeY() + yMove + getBounds().y + getBounds().height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (getRelativeX() + getBounds().x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (getRelativeX() + getBounds().x + getBounds().width) / Tile.TILEWIDTH, ty)) {
				setRelativeY(getRelativeY() + yMove);
			} else {
				setRelativeY(y = ty * Tile.TILEHEIGHT - getBounds().y - getBounds().height - 1);
			}

		}
	}

	protected BufferedImage getCurrentAnimationFrame(BufferedImage left, BufferedImage right, BufferedImage up,
			BufferedImage down) {

		if (sprinting) {

			if (xMove < 0) {
				steadyAnimation = left;
				return runningAnimLeft.getCurrentFrame();

			} else if (xMove > 0) {
				steadyAnimation = right;
				return runningAnimRight.getCurrentFrame();

			} else if (yMove < 0) {
				steadyAnimation = up;
				return runningAnimUp.getCurrentFrame();

			} else if (yMove > 0) {
				steadyAnimation = down;
				return runningAnimDown.getCurrentFrame();

			} else {
				return steadyAnimation;
			}

		} else {

			if (xMove < 0) {
				steadyAnimation = left;
				return walkingAnimLeft.getCurrentFrame();

			} else if (xMove > 0) {
				steadyAnimation = right;
				return walkingAnimRight.getCurrentFrame();

			} else if (yMove < 0) {
				steadyAnimation = up;
				return walkingAnimUp.getCurrentFrame();

			} else if (yMove > 0) {
				steadyAnimation = down;
				return walkingAnimDown.getCurrentFrame();

			} else {
				return steadyAnimation;
			}
		}
	}

	protected void getInput(boolean up, boolean down, boolean left, boolean right) {

		xMove = 0;
		yMove = 0;

		if (sprinting) {

			if (up)
				yMove = -runningSpeed;
			if (down)
				yMove = runningSpeed;
			if (left)
				xMove = -runningSpeed;
			if (right)
				xMove = runningSpeed;

		} else {

			if (up)
				yMove = -speed;
			if (down)
				yMove = speed;
			if (left)
				xMove = -speed;
			if (right)
				xMove = speed;
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	public void update() {
		super.update();

	}

	public BufferedImage getSteadyAnimation() {

		return steadyAnimation;

	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	

}