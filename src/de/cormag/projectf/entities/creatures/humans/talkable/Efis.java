package de.cormag.projectf.entities.creatures.humans.talkable;

import java.awt.Graphics;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class Efis extends TalkableHuman {

	private static final long serialVersionUID = 1L;

	public Efis(Handler handler, float x, float y) {
		super(handler, x, y, Player.DEFAULT_CREATURE_WIDTH, Player.DEFAULT_CREATURE_HEIGHT);

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, Assets.efis_down[1]);

	}

	@Override
	public void updateSpeech() {
		setSpeech("lul");

	}

}
