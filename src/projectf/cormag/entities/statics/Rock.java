package projectf.cormag.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Rectangle;

import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class Rock extends StaticEntity {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_ROCK_WIDTH = 32;
	public static final int DEFAULT_ROCK_HEIGHT = 32;

	// private Rectangle collisionBox;

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_ROCK_WIDTH, DEFAULT_ROCK_HEIGHT);

		// collisionBox =
		// getCollisionBounds(handler.getGameCamera().getxOffset(),
		// handler.getGameCamera().getyOffset());

	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.black);
		// g.fillRect((int) (collisionBox.x -
		// handler.getGameCamera().getxOffset()), (int) (collisionBox.y -
		// handler.getGameCamera().getyOffset()), collisionBox.width,
		// collisionBox.height);

		g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), null);

	}
}
