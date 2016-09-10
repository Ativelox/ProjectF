package de.cormag.projectf.entities.statics.music;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

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

	public void update() {
		super.update();

		musicTriggerRect = new Rectangle((int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()
						+ (HEIGHT - handler.getPlayer().getProperCollisionRectangle().height)),
				WIDTH, handler.getPlayer().getProperCollisionRectangle().height);

		if (!handler.getPlayer().getProperCollisionRectangle().intersects(musicTriggerRect)) {

			super.removeMusicChooseAreaIfOOR();

		}

	}

	public void render(Graphics g) {
		super.render(g, Assets.piano);

	}

}
