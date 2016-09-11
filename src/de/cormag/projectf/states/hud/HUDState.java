package de.cormag.projectf.states.hud;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.State;
import de.cormag.projectf.utils.time.GameTime;
import de.cormag.projectf.worlds.LoadingScreen;

public class HUDState extends State {

	private static final long serialVersionUID = 1L;

	private Set<HUDElement> hudElements;
	private Set<HUDElement> hudElementsToAdd;
	private Handler handler;

	private boolean activeBossHealthBar;

	public HUDState(Handler handler) {
		super(handler);

		this.handler = handler;

		activeBossHealthBar = false;

		hudElements = new HashSet<>();
		hudElementsToAdd = new HashSet<>();

		addHUDElement(new PlayerHealthBar(handler));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {

		for (HUDElement e : hudElements) {

			e.update(gameTime);

		}

		for (HUDElement e : hudElementsToAdd) {

			addHUDElement(e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(final Graphics g, final GameTime gameTime) {

		if (!(handler.getWorld() instanceof LoadingScreen)) {

			for (HUDElement e : hudElements) {
				e.render(g, gameTime);

			}
		}
	}

	@Override
	public boolean tickLower() {
		return true;
	}

	@Override
	public boolean renderLower() {
		return true;
	}

	public void addHUDElement(HUDElement hudElementToAdd) {

		if (hudElementToAdd instanceof BossHealthBar && activeBossHealthBar) {
			if (!hudElementsToAdd.contains(hudElementToAdd)) {
				hudElementsToAdd.add(hudElementToAdd);
				return;

			}
			return;

		}

		if (hudElementToAdd instanceof BossHealthBar) {

			activeBossHealthBar = true;

		}

		hudElements.add(hudElementToAdd);

	}

	public void removeHUDElement(HUDElement hudElementToRemove) {

		if (hudElementsToAdd.contains(hudElementToRemove)) {

			hudElementsToAdd.remove(hudElementToRemove);

		}

		if (hudElementToRemove instanceof BossHealthBar) {

			activeBossHealthBar = false;

		}

		hudElements.remove(hudElementToRemove);

	}

	public boolean containsHUDElement(HUDElement hudElementToCheck) {

		return hudElements.contains(hudElementToCheck);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}

}
