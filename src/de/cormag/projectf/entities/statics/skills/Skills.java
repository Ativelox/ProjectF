package de.cormag.projectf.entities.statics.skills;

import java.awt.Graphics;

import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.main.Handler;

public abstract class Skills extends StaticEntity{

	private static final long serialVersionUID = 1L;

	public Skills(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
	} 
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		
	}

	
}
