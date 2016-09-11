package de.cormag.projectf.worlds.buildings;

import java.awt.Graphics;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.utils.time.GameTime;

public class DefaultInn extends LeaveableBuilding {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public DefaultInn(Handler handler, String path) {
		super(handler, path, "DefaultInn");
		entityManager = new EntityManager(handler, handler.getPlayer());

		leaveableTileX = 12 * Tile.TILEWIDTH;
		leaveableTileY = 10 * Tile.TILEHEIGHT;

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
