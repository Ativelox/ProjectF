package projectf.cormag.worlds;

import java.awt.Graphics;

import projectf.cormag.entities.EntityManager;
import projectf.cormag.main.Handler;
import projectf.cormag.tiles.Tile;

public class DefaultInn extends LeaveableBuilding{

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;

	public DefaultInn(Handler handler, String path) {
		super(handler, path);
		entityManager = new EntityManager(handler, handler.getPlayer());
		
		leaveableTileX = 12 * Tile.TILEWIDTH;
		leaveableTileY = 10 * Tile.TILEHEIGHT;
		
		loadWorld(path);
	}
	
	public void tick(){
		super.tick();
		
		entityManager.tick();
	}
	
	public void render(Graphics g){
		super.render(g);
		
		entityManager.render(g);
		
	}
	
	@Override
	public EntityManager getEntityManager(){
		
		return entityManager;
		
	}

}
