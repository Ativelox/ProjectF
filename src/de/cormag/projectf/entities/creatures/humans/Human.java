package de.cormag.projectf.entities.creatures.humans;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.main.Handler;

public abstract class Human extends Creature {

	private static final long serialVersionUID = 1L;

	public Human(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

	}

}
