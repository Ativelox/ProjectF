package projectf.cormag.worlds;

import java.awt.Graphics;
import java.io.Serializable;

import projectf.cormag.entities.EntityManager;
import projectf.cormag.entities.statics.music.Piano;
import projectf.cormag.main.Handler;
import projectf.cormag.tiles.Tile;

public class DefaultHouse extends LeaveableBuilding implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public DefaultHouse(Handler handler, String path) {
		super(handler, path);
		
		this.path = path;

		player = handler.getPlayer();
		
		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(new Piano(handler, Tile.TILEWIDTH, Tile.TILEHEIGHT));
//		entityManager.addEntity(new Zombie(handler, 400, 400));
//		entityManager.addEntity(new DemonBoss(handler, 200, 200));
		
		leaveableTileX = 704;
		leaveableTileY = 640;

		loadWorld(path);
	}

	public void tick() {
		super.tick();
		
		entityManager.tick();

	}

	public void render(Graphics g) {
		super.render(g);
		
		entityManager.render(g);

	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
