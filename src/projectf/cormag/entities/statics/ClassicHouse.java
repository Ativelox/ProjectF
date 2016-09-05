package projectf.cormag.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class ClassicHouse extends StaticEntity {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_HOUSE_WIDTH = 128, DEFAULT_HOUSE_HEIGHT = 128;

	public ClassicHouse(Handler handler, float x, float y) {

		super(handler, x, y, DEFAULT_HOUSE_WIDTH, DEFAULT_HOUSE_HEIGHT);

		getBounds().height = DEFAULT_HOUSE_HEIGHT / 2;
		getBounds().y = DEFAULT_HOUSE_HEIGHT / 2;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.classic_house, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), null);
		g.setColor(Color.RED);
		

	}

}
