package de.cormag.projectf.entities.statics;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class VerdurousGreenTree extends StaticEntity{

	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 64, HEIGHT = 128;

	public VerdurousGreenTree(Handler handler, float x, float y) {
		super(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT, WIDTH, HEIGHT);
		
		getBounds().x = 20;
		getBounds().width = 24;
		getBounds().y = 104;
		getBounds().height = 20;
		
	}
	
	public void render(Graphics g){
		super.render(g, Assets.verdurousGreenTree, WIDTH, HEIGHT);
		
		
	}

}
