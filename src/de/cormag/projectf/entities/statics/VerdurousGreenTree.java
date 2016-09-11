package de.cormag.projectf.entities.statics;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.utils.time.GameTime;

public class VerdurousGreenTree extends StaticEntity {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 64, HEIGHT = 128;

	public VerdurousGreenTree(Handler handler, float x, float y) {
		super(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT, WIDTH, HEIGHT);

		getBounds().x = 20;
		getBounds().width = 24;
		getBounds().y = 104;
		getBounds().height = 20;

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, Assets.verdurousGreenTree);
	}

}
