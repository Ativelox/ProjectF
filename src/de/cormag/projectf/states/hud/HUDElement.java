package de.cormag.projectf.states.hud;

import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.properties.IUpdateable;

public abstract class HUDElement implements IUpdateable, IRenderable {
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.HUD_LAYER;
	}
}
