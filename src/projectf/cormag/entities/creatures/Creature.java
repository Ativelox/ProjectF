package projectf.cormag.entities.creatures;

import java.awt.image.BufferedImage;

import projectf.cormag.entities.Entity;
import projectf.cormag.gfx.Animation;
import projectf.cormag.main.Handler;
import projectf.cormag.tiles.Tile;

public abstract class Creature extends Entity {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	public int health;
	protected float speed;
	protected float runningSpeed;
	protected float xMove, yMove;
	protected int maxHealth;
	protected int attackValue;
	protected boolean damaged;

	protected transient Animation walkingAnimDown, walkingAnimUp, walkingAnimLeft, walkingAnimRight, runningAnimDown,
			runningAnimUp, runningAnimLeft, runningAnimRight;

	protected boolean sprinting;

	transient protected BufferedImage steadyAnimation;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		health = DEFAULT_HEALTH;
		maxHealth = DEFAULT_HEALTH;
		damaged = false;
		sprinting = false;
		runningSpeed = Creature.DEFAULT_SPEED * 2;
		xMove = 0;
		yMove = 0;

		getBounds().x = width / 3;
		getBounds().y = height / 3;
		getBounds().width = width / 3;
		getBounds().height = 2 * height / 3;
	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMove))
			moveY();
	}

	public void moveX() {
		if (xMove > 0) {// Moving right
			int tx = (int) (x + xMove + getBounds().x + getBounds().width) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + getBounds().y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + getBounds().y + getBounds().height) / Tile.TILEHEIGHT)) {
				x += xMove;
				hasMoved = true;
			} else {
				x = tx * Tile.TILEWIDTH - getBounds().x - getBounds().width - 1;
			}

		} else if (xMove < 0) {// Moving left
			int tx = (int) (x + xMove + getBounds().x) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + getBounds().y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + getBounds().y + getBounds().height) / Tile.TILEHEIGHT)) {
				x += xMove;
				hasMoved = true;
			} else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - getBounds().x;
			}

		}
	}

	public void moveY() {
		if (yMove < 0) {// Up
			int ty = (int) (y + yMove + getBounds().y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + getBounds().x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + getBounds().x + getBounds().width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
				hasMoved = true;
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - getBounds().y;
			}

		} else if (yMove > 0) {// Down
			int ty = (int) (y + yMove + getBounds().y + getBounds().height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + getBounds().x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + getBounds().x + getBounds().width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
				hasMoved = true;
			} else {
				y = ty * Tile.TILEHEIGHT - getBounds().y - getBounds().height - 1;
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
	
	public void tick(){
		super.tick();
		
	}

	// GETTERS SETTERS

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

	public int getHealth() {

		return health;

	}

	public void setHealth(int health) {

		this.health = health;
	}

	public int getAttackValue() {

		return attackValue;

	}

	public int getMaxHealth() {
		return maxHealth;

	}

	public boolean getDamaged() {

		return damaged;
	}

	public void setDagamed(boolean bool) {

		this.damaged = bool;

	}
}