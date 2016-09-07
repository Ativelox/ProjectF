package de.cormag.projectf.entities.statics.nocollision;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class GrassBushSmall extends NoCollision{
	
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 64, HEIGHT = 64;

	public GrassBushSmall(Handler handler, float x, float y) {
		super(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT, WIDTH, HEIGHT);
		
		getBounds().width = 0;
		getBounds().height = 0;
			
	}
	
	public void tick(){
		super.tick();
		
	}
	
	@Override
	public void render(Graphics g){
		super.render(g, Assets.grassBushSmall, WIDTH, HEIGHT);

		
	}

}
