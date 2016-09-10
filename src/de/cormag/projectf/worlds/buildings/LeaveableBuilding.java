package de.cormag.projectf.worlds.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.cormag.projectf.main.Handler;
import de.cormag.projectf.worlds.LoadingScreen;
import de.cormag.projectf.worlds.World;

public abstract class LeaveableBuilding extends World {

	private static final long serialVersionUID = 1L;

	protected Rectangle leaveableTileHitbox;

	protected int leaveableTileX, leaveableTileY;
	protected static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

	public LeaveableBuilding(Handler handler, String path, String name) {
		super(name);

		this.handler = handler;

		loadWorld(path);

		leaveableTileX = 0;
		leaveableTileY = 0;

		leaveableTileHitbox = new Rectangle(0, 0, 0, 0);

	}

	public void tick() {
		super.tick();

		leaveableTileHitbox = new Rectangle((int) (leaveableTileX - handler.getGameCamera().getxOffset()),
				(int) (leaveableTileY - handler.getGameCamera().getyOffset()), TILE_WIDTH, TILE_HEIGHT);

		if (checkPlayerLeaveableTileCollision()) {

			handler.setWorld(new LoadingScreen(handler.getLastWorld(), handler, handler.getPlayer().getRelativeX(),
					handler.getPlayer().getRelativeY()));

		}

	}

	public void render(Graphics g) {
		super.render(g);

		g.setColor(Color.RED);

		g.drawRect(leaveableTileHitbox.x, leaveableTileHitbox.y, leaveableTileHitbox.width, leaveableTileHitbox.height);

	}

	protected boolean checkPlayerLeaveableTileCollision() {

		if (leaveableTileHitbox.intersects(handler.getPlayer().getProperCollisionRectangle())) {

			return true;

		} else {
			return false;

		}
	}

	public int getLeaveableTileX() {

		return leaveableTileX;
	}

	public int getLeaveableTileY() {

		return leaveableTileY;
	}

}
