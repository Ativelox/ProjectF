package de.cormag.projectf.saves;

import java.io.Serializable;

import de.cormag.projectf.main.Game;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.worlds.World;
import de.cormag.projectf.worlds.buildings.DefaultHouse;
import de.cormag.projectf.worlds.music.TutorialDesert;
import de.cormag.projectf.worlds.music.TutorialFields;

public class SavedGame implements Serializable {

	private static final long serialVersionUID = 1L;

	private float xOffset, yOffset;

	private TutorialFields tutorialFields;
	private DefaultHouse tutorialHouse;
	private TutorialDesert tutorialDesert;
	private World currentWorld;

	public SavedGame(Game game, Handler handler) {

		this.tutorialFields = handler.getTutorialFields();
		this.tutorialHouse = handler.getDefaultHouse();
		this.tutorialDesert = handler.getTutorialDesert();
		this.currentWorld = handler.getWorld();

		this.xOffset = game.getGameCamera().getxOffset();
		this.yOffset = game.getGameCamera().getyOffset();

	}

	public TutorialFields getTutorialFields() {

		return this.tutorialFields;
	}

	public DefaultHouse getTutorialHouse() {

		return this.tutorialHouse;
	}

	public TutorialDesert getTutorialDesert() {

		return this.tutorialDesert;

	}

	public World getWorld() {

		return currentWorld;
	}

	public float getXOffset() {
		return xOffset;
	}

	public float getYOffset() {
		return yOffset;
	}

}
