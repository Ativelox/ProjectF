package de.cormag.projectf.entities.statics.buildings;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class StoneHouseFlat extends EnterableBuilding{
	
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
	
	public void tick(){
		super.tick();

		
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g, Assets.stoneHouseFlat, WIDTH, HEIGHT);
		
	}

}
