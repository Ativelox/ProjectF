package projectf.cormag.worlds.music;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import projectf.cormag.entities.EntityManager;
import projectf.cormag.entities.creatures.enemies.bosses.DemonBoss;
import projectf.cormag.entities.creatures.enemies.bosses.VikingBoss;
import projectf.cormag.entities.creatures.enemies.bosses.music.KingBoss;
import projectf.cormag.entities.creatures.humans.talkable.Efis;
import projectf.cormag.entities.creatures.humans.talkable.FemaleNPC;
import projectf.cormag.entities.statics.ClassicHouse;
import projectf.cormag.entities.statics.buildings.StoneHouseFlat;
import projectf.cormag.entities.statics.buildings.StoneHouseSemiFlat;
import projectf.cormag.entities.statics.buildings.StoneHouseSharp;
import projectf.cormag.main.Handler;
import projectf.cormag.sound.BGMPlayer;
import projectf.cormag.tiles.Tile;
import projectf.cormag.worlds.World;

public class TutorialFields extends MusicWorld implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;
	private ClassicHouse tutorialHouse;
	private StoneHouseSemiFlat semiFlatHouse;
	private StoneHouseSharp sharpHouse;
	private StoneHouseFlat flatHouseOne;
	private StoneHouseFlat flatHouseTwo;
	
	private StoneHouseSemiFlat semiFlatHouse2;
	private StoneHouseSemiFlat semiFlatHouse3;
	
	private Point comingFromDesert;

	private BGMPlayer soundPlayer;

	public TutorialFields(Handler handler, String path) {
		super();

		this.handler = handler;
		this.path = path;
		
		player = handler.getPlayer();
		
		semiFlatHouse = new StoneHouseSemiFlat(handler, 297, 80, 1);
		sharpHouse = new StoneHouseSharp(handler, 105, 50, 3);
		flatHouseOne = new StoneHouseFlat(handler, 687, 110, 2);
		flatHouseTwo = new StoneHouseFlat(handler, 880, 110, 2);
		semiFlatHouse2 = new StoneHouseSemiFlat(handler, 105, 400, 1);
		semiFlatHouse3 = new StoneHouseSemiFlat(handler, 297, 400, 1);

		entityManager = new EntityManager(handler, player);
		entityManager.addEntity(tutorialHouse);
		entityManager.addEntity(semiFlatHouse);
		entityManager.addEntity(sharpHouse);
		entityManager.addEntity(flatHouseOne);
		entityManager.addEntity(flatHouseTwo);
		entityManager.addEntity(semiFlatHouse2);
		entityManager.addEntity(semiFlatHouse3);
		entityManager.addEntity(new VikingBoss(handler, 550, 100));
		entityManager.addEntity(new KingBoss(handler, 100, 700));
		entityManager.addEntity(new DemonBoss(handler, 800, 500));
		entityManager.addEntity(new Efis(handler, 500, 400));
		entityManager.addEntity(new FemaleNPC(handler, 600, 400));
		
		loadWorld(path);

		player.setX(580);
		player.setY(450);
		
		soundPlayer = handler.getBGMPlayer();
		
		defaultSoundtrack = "Town.pfsf";
		
		comingFromDesert = new Point(25 * Tile.TILEWIDTH + handler.getPlayer().getWidth() / 2, handler.getPlayer().getHeight() + 1);

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
		
		changeWorldIfDemanded(handler.getTutorialDesert(), handler.getTutorialDesert().getComingFromFields());

		entityManager.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		entityManager.render(g);

	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public Point getComingFromDesert(){
		
		return comingFromDesert;
		
	}
}
