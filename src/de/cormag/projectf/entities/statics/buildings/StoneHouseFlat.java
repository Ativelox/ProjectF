package de.cormag.projectf.entities.statics.buildings;

import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class StoneHouseFlat extends EnterableBuilding{
	
	private static final long serialVersionUID = 1L;
	
	private static final int width = 170, height = 200;

	public StoneHouseFlat(Handler handler, int x, int y, int id) {
		super(handler, x, y, width, height, id);
		
		getBounds().height = width / 2;
		getBounds().y = height / 2;
		
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
		g.drawImage(Assets.stoneHouseFlat, (int) (x - xOffset), (int) (y - yOffset), width, height, null);
	
		super.render(g);
	}

}
