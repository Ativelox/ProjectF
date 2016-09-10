package de.cormag.projectf.entities.statics.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.Utils;
import de.cormag.projectf.worlds.LoadingScreen;

public abstract class EnterableBuilding extends StaticEntity {

	private static final long serialVersionUID = 1L;

	protected float doorX, doorY;
	protected int doorWidth, doorHeight;

	protected Rectangle doorHitbox;

	private int id;

	public EnterableBuilding(Handler handler, int x, int y, int width, int height, int id) {
		super(handler, x, y, width, height);

		doorX = 0;
		doorY = 0;
		doorWidth = 0;
		doorHeight = 0;

		this.id = id;

		doorHitbox = new Rectangle((int) (getX() + doorX), (int) (getY() + doorY), doorWidth, doorHeight);

	}

	public void update() {
		super.update();

		doorHitbox =  new Rectangle((int) (getX() + doorX), (int) (getY() + doorY), doorWidth, doorHeight);
		enterBuildingIfEntered();

	}

	public void render(Graphics g, BufferedImage imageToDraw) {
		super.render(g, imageToDraw);

		if (checkPlayerDoorCollision()) {

			Utils.openNotificationWindow(g, "Enter (E)");

		}

		renderDoorHitbox(g);

	}

	protected void renderDoorHitbox(Graphics g) {

		g.setColor(Color.RED);
		g.drawRect(doorHitbox.x, doorHitbox.y, doorHitbox.width, doorHitbox.height);

	}

	protected boolean checkPlayerDoorCollision() {

		if (doorHitbox.intersects(handler.getPlayer().getProperCollisionRectangle())) {
			return true;

		} else {
			return false;

		}
	}

	protected void enterBuildingIfEntered() {

		switch (id) {

		// Default House
		case 1:
			if (handler.getKeyManager().e && checkPlayerDoorCollision()) {
				handler.setWorld(new LoadingScreen(handler.getDefaultHouse(), handler,
						handler.getDefaultHouse().getLeaveableTileX(),
						handler.getDefaultHouse().getLeaveableTileY() - handler.getPlayer().getHeight()));
			}
			break;
		// Weapon/Armory Shop
		case 2:
			if (handler.getKeyManager().e && checkPlayerDoorCollision()) {
				handler.setWorld(
						new LoadingScreen(handler.getDefaultInn(), handler, handler.getDefaultInn().getLeaveableTileX(),
								handler.getDefaultInn().getLeaveableTileY() - handler.getPlayer().getHeight()));
			}
			break;

		// Default House with decoration i guess
		case 3:

			// tbd
		case 4:

		}

	}

}
