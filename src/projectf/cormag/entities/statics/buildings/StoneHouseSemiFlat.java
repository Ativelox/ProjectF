package projectf.cormag.entities.statics.buildings;

import java.awt.Graphics;

import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class StoneHouseSemiFlat extends EnterableBuilding{

	private static final long serialVersionUID = 1L;
	
	private static final int width = 180, height = 230;

	public StoneHouseSemiFlat(Handler handler, int x, int y, int id) {
		super(handler, x, y, width, height, id);
		
		getBounds().height = width / 2;
		getBounds().y = height / 2;
		
		doorX = 40;
		doorY = 163;
		doorWidth = 32;
		doorHeight = 65;
		
	}
	
	public void tick(){
		super.tick();

		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.stoneHouseSemiFlat, (int) (x - xOffset), (int) (y - yOffset), width, height, null);
		
		super.render(g);
	}

}
