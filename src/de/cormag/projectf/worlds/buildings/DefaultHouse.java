package de.cormag.projectf.worlds.buildings;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.statics.music.Piano;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.utils.time.GameTime;

public class DefaultHouse extends LeaveableBuilding implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public DefaultHouse(Handler handler, String path) {
		super(handler, path, "DefaultHouse");

		this.path = path;

		player = handler.getPlayer();

		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(new Piano(handler, Tile.TILEWIDTH, Tile.TILEHEIGHT));
		// entityManager.addEntity(new Zombie(handler, 400, 400));
		// entityManager.addEntity(new DemonBoss(handler, 200, 200));

		leaveableTileX = 704;
		leaveableTileY = 640;

		loadWorld(path);
	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

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

	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}
}
