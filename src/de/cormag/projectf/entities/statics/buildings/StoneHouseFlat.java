package de.cormag.projectf.entities.statics.buildings;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class StoneHouseFlat extends EnterableBuilding {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 170, HEIGHT = 200;

	public StoneHouseFlat(Handler handler, int x, int y, int id) {
		super(handler, x, y, WIDTH, HEIGHT, id);

		this.x = x;
		this.y = y;

		getBounds().height = WIDTH / 2;
		getBounds().y = HEIGHT / 2;

		doorX = 35;
		doorY = 135;
		doorWidth = 32;
		doorHeight = 65;

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, Assets.stoneHouseFlat);

	}

}
