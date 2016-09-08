package de.cormag.projectf.worlds.music;

import java.awt.Graphics;
import java.awt.Point;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class WoodsOfDeception extends MusicWorld{

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	private Point comingFromFieldOne;

	public WoodsOfDeception(Handler handler, String path) {
		super(handler.getBGMPlayer(), "WoodsOfDeception");
		
		this.handler = handler;
		this.path = path;

		player = handler.getPlayer();
		
		entityManager = new EntityManager(handler, player);
		
		loadWorld(path);
		
		createWorldBoundary(entityManager);
		createGreenWoods(entityManager, 1, 0, width - 1, height - 1);
		
		defaultSoundtrack = "WoodsOfDeception.pfsf";
		
		comingFromFieldOne = new Point(1 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2, 1);
	}
	
	public void tick(){
		super.tick();
		
		changeWorldIfDemanded(handler.getFieldOne().getComingFromWoodsOfDeception(), Tile.grassTeleportFWoodsOfDeceptionTFieldOne);
		
		entityManager.tick();
	}
	
	public void render(Graphics g){
		super.render(g);
		
		entityManager.render(g);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public Point getComingFromFieldOne(){
		return comingFromFieldOne;
		
	}

}
