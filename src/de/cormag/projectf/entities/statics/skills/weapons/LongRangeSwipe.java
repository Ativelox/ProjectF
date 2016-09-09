package de.cormag.projectf.entities.statics.skills.weapons;

import java.awt.Graphics;

import de.cormag.projectf.entities.statics.skills.Skills;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class LongRangeSwipe extends Skills{

	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 128, HEIGHT = 32;

	public LongRangeSwipe(Handler handler, float x, float y) {
		super(handler, x, y, WIDTH, HEIGHT);
		
	}
	
	public void tick(){
		
		
	}
	
	public void render(Graphics g){
		g.drawImage(Assets.longRangeSwipe, (int) (x - xOffset), (int) (y - yOffset), WIDTH, HEIGHT, null);
		
		
	}

}
