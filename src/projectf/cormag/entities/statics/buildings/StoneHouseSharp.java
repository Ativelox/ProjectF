package projectf.cormag.entities.statics.buildings;

import java.awt.Graphics;

import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class StoneHouseSharp extends EnterableBuilding{

	private static final long serialVersionUID = 1L;
	
	private static final int width = 180 , height = 260 ;

	public StoneHouseSharp(Handler handler, int x, int y, int id) {
		super(handler, x, y, width, height, id);
		
		getBounds().height = width / 2;
		getBounds().y = height / 2 + 10;
		
		doorX = 40;
		doorY = 194;
		doorWidth = 32;
		doorHeight = 65;
		
	}
	
	public void tick(){
		super.tick();


	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.stoneHouseSharp, (int) (x - xOffset), (int) (y - yOffset), width, height, null);
		
		super.render(g);

	}

}
