package de.cormag.projectf.entities.statics.buildings;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class StoneHouseSharp extends EnterableBuilding {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 180, HEIGHT = 260;

	public StoneHouseSharp(Handler handler, int x, int y, int id) {
		super(handler, x, y, WIDTH, HEIGHT, id);

		this.x = x;
		this.y = y;

		getBounds().height = WIDTH / 2;
		getBounds().y = HEIGHT / 2 + 10;

		doorX = 40;
		doorY = 194;
		doorWidth = 32;
		doorHeight = 65;

	}

	public void update() {
		super.update();

	}

	@Override
	public void render(Graphics g) {
		super.render(g, Assets.stoneHouseSharp, WIDTH, HEIGHT);

	}

}
