package projectf.cormag.entities.creatures.humans.talkable;

import java.awt.Color;
import java.awt.Graphics;

import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class Efis extends TalkableHuman{

	private static final long serialVersionUID = 1L;

	public Efis(Handler handler, float x, float y) {
		super(handler, x, y, Player.DEFAULT_CREATURE_WIDTH, Player.DEFAULT_CREATURE_HEIGHT);
	
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.efis_down[1], (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		g.setColor(Color.black);
		
		super.render(g);

	}

	@Override
	public void updateSpeech() {
		setSpeech("lul");
		
	}

}
