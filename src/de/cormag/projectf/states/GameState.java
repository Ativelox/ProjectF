package de.cormag.projectf.states;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.saves.SavedGame;
import de.cormag.projectf.states.hud.HUDState;
import de.cormag.projectf.utils.time.GameTime;
import de.cormag.projectf.worlds.World;
import de.cormag.projectf.worlds.buildings.DefaultHouse;
import de.cormag.projectf.worlds.music.TutorialDesert;
import de.cormag.projectf.worlds.music.TutorialFields;

public class GameState extends State implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<World> worldList;

	private TutorialFields tutorialFields;
	private DefaultHouse tutorialHouse;
	private TutorialDesert tutorialDesert;

	private transient HUDState hudState;

	public GameState(Handler handler) {
		super(handler);

		this.tutorialFields = handler.getTutorialFields();
		this.tutorialHouse = handler.getDefaultHouse();
		this.tutorialDesert = handler.getTutorialDesert();

		handler.setWorld(tutorialFields);

		worldList = new LinkedList<>();
		worldList.add(tutorialFields);
		worldList.add(tutorialHouse);
	}

	public GameState(Handler handler, SavedGame savedGame) {
		super(handler);

		handler.setWorld(savedGame.getWorld());

		tutorialFields = savedGame.getTutorialFields();
		tutorialHouse = savedGame.getTutorialHouse();
		tutorialDesert = savedGame.getTutorialDesert();

		worldList = new LinkedList<>();
		worldList.add(tutorialFields);
		worldList.add(tutorialHouse);
		worldList.add(tutorialDesert);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {
		handler.getWorld().update(gameTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(final Graphics g, final GameTime gameTime) {
		handler.getWorld().render(g, gameTime);
	}

	public void createHUD() {

		hudState = new HUDState(handler);

		handler.getGame().getStateManager().push(hudState);

	}

	public HUDState getHUDState() {

		return hudState;

	}

	public Iterator<World> getWorldIterator() {

		return worldList.iterator();

	}

	public World getWorld() {

		return handler.getWorld();

	}

	@Override
	public boolean tickLower() {
		return false;
	}

	@Override
	public boolean renderLower() {
		return false;
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