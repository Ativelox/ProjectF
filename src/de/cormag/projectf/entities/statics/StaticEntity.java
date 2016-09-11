package de.cormag.projectf.entities.statics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public abstract class StaticEntity extends Entity {

	private static final long serialVersionUID = 1L;

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

	}

	public void render(Graphics g, final GameTime gameTime, BufferedImage imageToDraw) {
		g.drawImage(imageToDraw, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
		super.render(g, gameTime);

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

	}

}
