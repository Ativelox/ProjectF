package projectf.cormag.gfx;

import projectf.cormag.entities.Entity;
import projectf.cormag.main.Game;
import projectf.cormag.main.Handler;
import projectf.cormag.tiles.Tile;

public class GameCamera {

	private Handler handler;
	private float xOffset, yOffset;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void checkBlankSpace() {
		
		if (xOffset < 0) {
			xOffset = 0;
		}else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - Game.WIDTH) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - Game.WIDTH;
		}

		if (yOffset < 0) {
			yOffset = 0;
		}else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - Game.HEIGHT) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - Game.HEIGHT;
		}
	}

	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - Game.WIDTH / 2 + e.getWidth() / 2;
		yOffset = e.getY() - Game.HEIGHT / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
