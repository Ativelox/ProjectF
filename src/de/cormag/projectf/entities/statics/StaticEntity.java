package de.cormag.projectf.entities.statics;

import java.awt.Graphics;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.main.Handler;

public abstract class StaticEntity extends Entity {

	private static final long serialVersionUID = 1L;

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	public void render(Graphics g){
		super.render(g);
		
		
	}
	
	public void tick(){
		super.tick();
	
	}

}
