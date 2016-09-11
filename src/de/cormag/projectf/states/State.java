package de.cormag.projectf.states;

import java.io.Serializable;

import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.properties.IUpdateable;
import de.cormag.projectf.main.Handler;

public abstract class State implements Serializable, IUpdateable, IRenderable {

	private static final long serialVersionUID = 1L;

	// HANDLER

	protected transient Handler handler;

	public State(Handler handler) {
		this.handler = handler;

	}

	public abstract boolean tickLower();

	public abstract boolean renderLower();

}
