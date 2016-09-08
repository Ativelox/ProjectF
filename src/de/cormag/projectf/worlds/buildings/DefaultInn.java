package de.cormag.projectf.worlds.buildings;

import java.awt.Graphics;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class DefaultInn extends LeaveableBuilding{

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;

	public DefaultInn(Handler handler, String path) {
		super(handler, path, "DefaultInn");
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
