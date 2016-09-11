package de.cormag.projectf.entities.statics.music;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class Piano extends MusicPlayingDevice {

	private static final long serialVersionUID = 1L;

	private Handler handler;

	public static final int WIDTH = 64 * 2, HEIGHT = 75 * 2;

	public Piano(Handler handler, float x, float y) {
		super(handler, x, y, WIDTH, HEIGHT);

		this.handler = handler;

		musicTriggerRect = new Rectangle((int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()
						+ (HEIGHT - handler.getPlayer().getProperCollisionRectangle().height)),
				WIDTH, handler.getPlayer().getProperCollisionRectangle().height);

		getBounds().height = HEIGHT - handler.getPlayer().getProperCollisionRectangle().height;
	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		musicTriggerRect = new Rectangle((int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()
						+ (HEIGHT - handler.getPlayer().getProperCollisionRectangle().height)),
				WIDTH, handler.getPlayer().getProperCollisionRectangle().height);

		if (!handler.getPlayer().getProperCollisionRectangle().intersects(musicTriggerRect)) {

			super.removeMusicChooseAreaIfOOR();

		}

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, Assets.piano);

	}

}
