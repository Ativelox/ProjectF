package de.cormag.projectf.entities.statics;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class FenceYLeftAlign extends StaticEntity {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 10, HEIGHT = 64;

	public FenceYLeftAlign(Handler handler, int x, int y) {
		super(handler, x * Tile.TILEWIDTH - Tile.TILEWIDTH, y * Tile.TILEHEIGHT, WIDTH, HEIGHT);

	}

	public void render(Graphics g) {
		super.render(g, Assets.fenceY, WIDTH, HEIGHT);

	}

}
