package de.cormag.projectf.worlds.music;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.humans.talkable.Efis;
import de.cormag.projectf.entities.creatures.humans.talkable.FemaleNPC;
import de.cormag.projectf.entities.particles.Glow;
import de.cormag.projectf.entities.statics.buildings.StoneHouseFlat;
import de.cormag.projectf.entities.statics.buildings.StoneHouseSemiFlat;
import de.cormag.projectf.entities.statics.buildings.StoneHouseSharp;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class TutorialFields extends MusicWorld implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;
	private StoneHouseSemiFlat semiFlatHouse;
	private StoneHouseSharp sharpHouse;
	private StoneHouseFlat flatHouseOne;
	private StoneHouseFlat flatHouseTwo;

	private StoneHouseSemiFlat semiFlatHouse2;
	private StoneHouseSemiFlat semiFlatHouse3;

	private Point comingFromDesert, comingFromFieldOne;

	public TutorialFields(Handler handler, String path) {
		super(handler.getBGMPlayer(), "TutorialFields");

		this.handler = handler;
		this.path = path;

		player = handler.getPlayer();

		semiFlatHouse = new StoneHouseSemiFlat(handler, 297, 80, 1);
		sharpHouse = new StoneHouseSharp(handler, 105, 50, 3);
		flatHouseOne = new StoneHouseFlat(handler, 687, 110, 2);
		flatHouseTwo = new StoneHouseFlat(handler, 880, 110, 2);
		semiFlatHouse2 = new StoneHouseSemiFlat(handler, 105, 400, 1);
		semiFlatHouse3 = new StoneHouseSemiFlat(handler, 297, 400, 1);

		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(semiFlatHouse);
		entityManager.addEntity(sharpHouse);
		entityManager.addEntity(flatHouseOne);
		entityManager.addEntity(flatHouseTwo);
		entityManager.addEntity(semiFlatHouse2);
		entityManager.addEntity(semiFlatHouse3);
		entityManager.addEntity(new Efis(handler, 500, 400));
		entityManager.addEntity(new FemaleNPC(handler, 600, 400));

		// Add particles
		entityManager.addEntity(new Glow(handler, 700, 400));

		loadWorld(path);

		player.setRelativeX(580);
		player.setRelativeY(450);

		defaultSoundtrack = "Town.pfsf";

		int[] spaces = { 6, 7 };

		createWorldBoundary(entityManager);
		createAdditionalXBoundaryTopAlign(entityManager, 1, width - 1, 15, spaces);
		createAdditionalYBoundaryLeftAlign(entityManager, 16, 21, 8, null);
		createAdditionalYBoundaryRightAlign(entityManager, 16, 21, 5, null);

		comingFromDesert = new Point(25 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2, 1);
		comingFromFieldOne = new Point(6 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2, 20 * Tile.TILEHEIGHT);

	}

	@Override
	public void tick() {
		super.tick();

		changeWorldIfDemanded(handler.getTutorialDesert().getComingFromTutorialFields(),
				Tile.snowTeleportFTutorialFieldsTTutorialDesert);
		changeWorldIfDemanded(handler.getFieldOne().getComingFromTutorialFields(),
				Tile.snowTeleportFTutorialFieldsTFieldOne);

		entityManager.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		entityManager.render(g);

	}

	public Point getComingFromDesert() {

		return comingFromDesert;

	}

	@Override
	public EntityManager getEntityManager() {

		return entityManager;

	}

	public Point getComingFromFieldOne() {
		return comingFromFieldOne;
	}
}
