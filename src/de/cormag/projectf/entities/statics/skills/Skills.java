package de.cormag.projectf.entities.statics.skills;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public abstract class Skills extends StaticEntity {

	private static final long serialVersionUID = 1L;
	
	private int dmg;

	public Skills(Handler handler, float x, float y, int width, int height, int dmg) {
		super(handler, x, y, width, height);
		
		this.dmg = dmg;

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);
		
	}

	@Override
	public void render(Graphics g, final GameTime gameTime, BufferedImage imageToDraw) {
		super.render(g, gameTime, imageToDraw);

	}
	
	public int getDMG(){
		
		return dmg;
		
	}

}
