package de.cormag.projectf.entities.statics.nocollision;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class GrassBushLarge extends NoCollision{

	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 128, HEIGHT = 128;

	public GrassBushLarge(Handler handler, float x, float y) {
		super(handler, x * Tile.TILEHEIGHT, y * Tile.TILEWIDTH, WIDTH, HEIGHT);
		
		getBounds().width = 0;
		getBounds().height = 0;
	}
	
	@Override
	public void render(Graphics g){
		super.render(g, Assets.grassBushLarge, WIDTH, HEIGHT);
		
	}

}
