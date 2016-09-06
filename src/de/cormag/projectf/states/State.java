package de.cormag.projectf.states;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.main.Handler;

public abstract class State implements Serializable {

	private static final long serialVersionUID = 1L;

	// HANDLER

	protected transient Handler handler;

	public State(Handler handler) {
		this.handler = handler;

	}

	public abstract boolean tickLower();

	public abstract boolean renderLower();

	public abstract void tick();

	public abstract void render(Graphics g);

}
