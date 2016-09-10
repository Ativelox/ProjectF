package de.cormag.projectf.entities.statics;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class FenceXBottomAlign extends StaticEntity {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 64, HEIGHT = 55;

	public FenceXBottomAlign(Handler handler, float x, float y) {
		super(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT + (Tile.TILEHEIGHT - HEIGHT), WIDTH, HEIGHT);

		getBounds().height = 20;
		getBounds().y = HEIGHT - getBounds().height;
	}

	public void render(Graphics g) {
		super.render(g, Assets.fenceX);

	}

}
