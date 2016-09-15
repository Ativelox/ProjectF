package de.cormag.projectf.entities.creatures.humans.talkable;

import java.awt.Graphics;
//import java.awt.Rectangle;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class FemaleNPC extends TalkableHuman {

	private static final long serialVersionUID = 1L;

	public FemaleNPC(Handler handler, float x, float y) {
		super(handler, x, y, Player.DEFAULT_CREATURE_WIDTH, Player.DEFAULT_CREATURE_HEIGHT);

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {

		super.render(g, gameTime, Assets.female_NPC);

	}

	@Override
	public void updateSpeech() {
		setSpeech("haha");

	}

}
