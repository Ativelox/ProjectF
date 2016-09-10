package de.cormag.projectf.entities.creatures.humans.talkable;

import java.awt.Graphics;
//import java.awt.Rectangle;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class FemaleNPC extends TalkableHuman {

	private static final long serialVersionUID = 1L;

	public FemaleNPC(Handler handler, float x, float y) {
		super(handler, x, y, Player.DEFAULT_CREATURE_WIDTH, Player.DEFAULT_CREATURE_HEIGHT);

	}
	
	public void render(Graphics g) {

		super.render(g, Assets.female_NPC);

	}

	@Override
	public void updateSpeech() {
		setSpeech("haha");

	}

}
