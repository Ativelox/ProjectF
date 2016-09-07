package de.cormag.projectf.entities.creatures.humans.talkable;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Rectangle;

import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class FemaleNPC extends TalkableHuman {

	private static final long serialVersionUID = 1L;

	public FemaleNPC(Handler handler, float x, float y) {
		super(handler, x, y, Player.DEFAULT_CREATURE_WIDTH, Player.DEFAULT_CREATURE_HEIGHT);

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.female_NPC, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		g.setColor(Color.black);
		
		super.render(g);

	}

	@Override
	public void updateSpeech() {
		setSpeech("haha");
		
	}

}