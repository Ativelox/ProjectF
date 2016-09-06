package de.cormag.projectf.worlds.music;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.enemies.bosses.music.DragonBoss;
import de.cormag.projectf.entities.creatures.humans.talkable.FemaleNPC;
import de.cormag.projectf.entities.statics.buildings.StoneHouseSemiFlat;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.tiles.Tile;

public class TutorialDesert extends MusicWorld implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;
	
	private BGMPlayer soundPlayer;
	private StoneHouseSemiFlat semiFlatHouse;
	
	private Point comingFromFields;

	public TutorialDesert(Handler handler, String path) {
		super();

		this.handler = handler;
		this.path = path;

		player = handler.getPlayer();
		
		semiFlatHouse = new StoneHouseSemiFlat(handler, 300, 100, 1);

		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(new DragonBoss(handler, 1100, 100));
		entityManager.addEntity(semiFlatHouse);
		entityManager.addEntity(new FemaleNPC(handler, 500, 200));

		loadWorld(path);
		
		soundPlayer = handler.getBGMPlayer();
		
		defaultSoundtrack = "GourmetRace.pfsf";
		
		isRunning = false;
		
		comingFromFields = new Point(25 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2, 14 * Tile.TILEHEIGHT - handler.getPlayer().getHeight());

	}

	@Override
	public void tick() {
		super.tick();
		
		if(!isRunning){
			
			soundPlayer.setSound(getDefaultSoundtrack());
			soundPlayer.playSound();
			isRunning = true;
			
		}
		
		soundPlayer.tick();
		
		changeWorldIfDemanded(handler.getTutorialFields(), handler.getTutorialFields().getComingFromDesert());

		entityManager.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		entityManager.render(g);

	}

	@Override
	public EntityManager getEntityManager() {

		return entityManager;

	}
	
	public Point getComingFromFields(){
		
		return comingFromFields;
		
	}
	
}
