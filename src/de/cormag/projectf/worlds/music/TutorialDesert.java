package de.cormag.projectf.worlds.music;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.enemies.bosses.music.DragonBoss;
import de.cormag.projectf.entities.creatures.humans.talkable.FemaleNPC;
import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.statics.buildings.StoneHouseSemiFlat;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.utils.time.GameTime;

public class TutorialDesert extends MusicWorld implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	private StoneHouseSemiFlat semiFlatHouse;

	private Point comingFromTutorialFields;

	public TutorialDesert(Handler handler, String path) {
		super(handler.getBGMPlayer(), "TutorialDesert");

		this.handler = handler;
		this.path = path;

		player = handler.getPlayer();

		semiFlatHouse = new StoneHouseSemiFlat(handler, 300, 100, 1);

		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(new DragonBoss(handler, 1100, 100));
		entityManager.addEntity(semiFlatHouse);
		entityManager.addEntity(new FemaleNPC(handler, 500, 200));

		loadWorld(path);

		createWorldBoundary(entityManager);

		defaultSoundtrack = "GourmetRace.pfsf";

		comingFromTutorialFields = new Point(25 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2,
				15 * Tile.TILEHEIGHT - handler.getPlayer().getHeight());

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		changeWorldIfDemanded(handler.getTutorialFields().getComingFromDesert(),
				Tile.sandTeleportFTutorialDesertTTutorialFields);

		entityManager.update(gameTime);

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime);

		entityManager.render(g, gameTime);

	}

	@Override
	public EntityManager getEntityManager() {

		return entityManager;

	}

	public Point getComingFromTutorialFields() {

		return comingFromTutorialFields;

	}

	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}

}
