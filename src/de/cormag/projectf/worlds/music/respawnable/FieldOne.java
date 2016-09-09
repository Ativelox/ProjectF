package de.cormag.projectf.worlds.music.respawnable;

import java.awt.Graphics;
import java.awt.Point;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.enemies.monster.Zombie;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class FieldOne extends RespawnableWorld {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;
	private Point comingFromTutorialFields;
	private Point comingFromWoodsOfDeception;

	public FieldOne(Handler handler, String path) {
		super(handler.getBGMPlayer(), "FieldOne");

		this.handler = handler;
		this.path = path;

		player = handler.getPlayer();

		loadWorld(path);

		defaultSoundtrack = "BattleTheme.pfsf";

		isRunning = false;

		comingFromTutorialFields = new Point(37 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2, 1);
		comingFromWoodsOfDeception = new Point(37 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2,
				((height - 2) * Tile.TILEHEIGHT) - 1);
	}

	@Override
	public void tick() {
		super.tick();

		changeWorldIfDemanded(handler.getTutorialFields().getComingFromFieldOne(),
				Tile.dirtTeleportFFieldOneTTutorialFields);
		changeWorldIfDemanded(handler.getWoodsOfDeception().getComingFromFieldOne(),
				Tile.grassTeleportFFieldOneTWoodsOfDeception);

		entityManager.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		entityManager.render(g);

	}

	public Point getComingFromTutorialFields() {

		return comingFromTutorialFields;

	}

	public Point getComingFromWoodsOfDeception() {

		return comingFromWoodsOfDeception;

	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public EntityManager getInitializedEntityManager() {

		EntityManager initializedEntityManager = new EntityManager(handler, player);

		for (int i = 1; i < width - 5; i += 5) {

			for (int j = 1; j < height - 1; j += 5) {

				initializedEntityManager.addEntity(new Zombie(handler, i, j));

			}

		}

		createGreenWoods(initializedEntityManager, 27, 25, 39, 41);

		createGrassBushField(initializedEntityManager, 1, 1, 14, 14);

		createWorldBoundary(initializedEntityManager);

		return initializedEntityManager;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

}
