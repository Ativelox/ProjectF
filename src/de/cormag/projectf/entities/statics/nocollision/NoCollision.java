package de.cormag.projectf.entities.statics.nocollision;

import java.awt.Graphics;

import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.main.Handler;

public abstract class NoCollision extends StaticEntity {

	private static final long serialVersionUID = 1L;

	public NoCollision(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		getBounds().width = 0;
		getBounds().height = 0;
	}

	@Override
	protected void renderHitBox(Graphics g) {

	}
	
	@Override
	public int getLayer(){
		return IRenderable.BACK_LAYER;
		
	}
}
