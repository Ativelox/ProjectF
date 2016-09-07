package de.cormag.projectf.worlds.music.respawnable;

import java.awt.Graphics;
import java.awt.Point;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.enemies.monster.Zombie;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;

public class FieldOne extends RespawnableWorld{

	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	private Point comingFromTutorialFields;

	public FieldOne(Handler handler, String path) {
		super(handler.getBGMPlayer());
		
		this.handler = handler;
		this.path = path;

		player = handler.getPlayer();
		
		loadWorld(path);
		
		defaultSoundtrack = "Intro.pfsf";
		
		isRunning = false;

		comingFromTutorialFields = new Point(37 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2, 1);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		changeWorldIfDemanded(handler.getTutorialFields().getComingFromFieldOne(), Tile.grassTeleportFFieldsOneTTutorialFields);

		entityManager.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		entityManager.render(g);

	}
	
	public Point getComingFromTutorialFields(){
		
		return comingFromTutorialFields;
		
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public EntityManager getInitializedEntityManager() {
		
		
		EntityManager initializedEntityManager = new EntityManager(handler, player);
		for(int i = 0; i < width; i += 5){
			initializedEntityManager.addEntity(new Zombie(handler, i * Tile.TILEWIDTH, (height - i) * Tile.TILEHEIGHT));
			
			initializedEntityManager.addEntity(new Zombie(handler, i * Tile.TILEWIDTH, height / 2 * Tile.TILEHEIGHT));
		}
		return initializedEntityManager;
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		
		this.entityManager = entityManager;
		
	}


}
