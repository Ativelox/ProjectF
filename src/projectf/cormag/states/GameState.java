package projectf.cormag.states;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import projectf.cormag.main.Handler;
import projectf.cormag.saves.SavedGame;
import projectf.cormag.states.hud.HUDState;
import projectf.cormag.worlds.World;
import projectf.cormag.worlds.buildings.DefaultHouse;
import projectf.cormag.worlds.music.TutorialDesert;
import projectf.cormag.worlds.music.TutorialFields;

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

	@Override
	public void tick() {
		handler.getWorld().tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getWorld().render(g);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean renderLower() {
		// TODO Auto-generated method stub
		return false;
	}

}