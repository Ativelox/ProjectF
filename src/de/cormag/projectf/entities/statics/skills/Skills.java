package de.cormag.projectf.entities.statics.skills;

import java.awt.Graphics;

import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.main.Handler;

public abstract class Skills extends StaticEntity {

	private static final long serialVersionUID = 1L;
	
	private int dmg;

	public Skills(Handler handler, float x, float y, int width, int height, int dmg) {
		super(handler, x, y, width, height);
		
		this.dmg = dmg;

	}

	public void update() {
		super.update();
		
	}

	public void render(Graphics g) {
		super.render(g);

	}
	
	public int getDMG(){
		
		return dmg;
		
	}

}
