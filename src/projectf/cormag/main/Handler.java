package projectf.cormag.main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

import projectf.cormag.entities.Entity;
import projectf.cormag.entities.creatures.enemies.bosses.Boss;
import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.GameCamera;
import projectf.cormag.input.KeyManager;
import projectf.cormag.sound.BGMPlayer;
import projectf.cormag.worlds.LoadingScreen;
import projectf.cormag.worlds.World;
import projectf.cormag.worlds.buildings.DefaultHouse;
import projectf.cormag.worlds.buildings.DefaultInn;
import projectf.cormag.worlds.music.MusicWorld;
import projectf.cormag.worlds.music.TutorialDesert;
import projectf.cormag.worlds.music.TutorialFields;

public class Handler {

	private Game game;
	private World world;
	private World lastWorld;
	private BGMPlayer bgmPlayer;
	private Player player;
	
	private GameCamera gameCamera;
	
	private TutorialFields tutorialFields;
	private DefaultHouse defaultHouse;
	private TutorialDesert tutorialDesert;
	private DefaultInn defaultInn;
	
	private LinkedList<World> worldList;
	
	

	public Handler(Game game) {
		this.game = game;
		
		bgmPlayer = new BGMPlayer(this);
		
		worldList = new LinkedList<>();
		
		player = new Player(this, 200, 200);
		gameCamera = new GameCamera(this, 0, 0);
		
		tutorialFields = new TutorialFields(this, "TutorialFields.pflf");
		defaultHouse = new DefaultHouse(this, "defaultHouse.pflf");
		tutorialDesert = new TutorialDesert(this, "TutorialDesert.pflf");
		defaultInn = new DefaultInn(this, "defaultInn.pflf");
		
		worldList.add(tutorialFields);
		worldList.add(tutorialDesert);
	
	}
	
	public LinkedList<World> getWorldList(){
		
		return worldList;
		
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		
		if(!(this.world instanceof LoadingScreen)){
			lastWorld = this.world;
		}
		
		if(this.world != null && this.world.getEntityManager() != null){
		
			Iterator <Entity> entities = this.world.getEntityManager().getEntities();
			
			while(entities.hasNext()){
				
				Entity e = entities.next();
				
				if(e instanceof Boss){
					
					((Boss) e).removeHealthBar(((Boss) e).getBossHealthBar());
					
				}
				
			}
		}

		this.world = world;
		
//		if(world instanceof MusicWorld){
//			
//			World[] worlds = bgmPlayer.getVisitedMusicWorlds();
//			Clip[] clips = bgmPlayer.getVisitedMusicWorldsClip();
//			
//			if(worlds != null && clips != null){
//
//				for(int j = 0; j < worlds.length; j++){
//					
//					if(worlds[j] != null && worlds[j].equals(world)){
//						
//						final int i = j;
//						
//						Timer timer = new Timer();
//						timer.schedule(new TimerTask(){
//
//							@Override
//							public void run() {
//								
//								bgmPlayer.setClip(clips[i]);
//								
//							}
//			
//						}, 50);
//					}
//					
//					
//				}
//
//			}
//		}
			
	}
	
	
	
	public World getLastWorld(){
		
		return lastWorld;
		
	}
	
	public BGMPlayer getBGMPlayer(){
		
		return bgmPlayer;
		
	}
	
	public void setBGMPlayer(BGMPlayer bgmPlayer){
		
		this.bgmPlayer = bgmPlayer;
		
	}
	
	public Player getPlayer(){
		
		return this.player;
		
	}
	
	public void setPlayer(Player player){
		
		this.player = player;
		
	}
	
	public TutorialFields getTutorialFields() {

		return tutorialFields;

	}

	public DefaultHouse getDefaultHouse() {

		return defaultHouse;

	}

	public TutorialDesert getTutorialDesert() {

		return tutorialDesert;

	}
	
	public DefaultInn getDefaultInn(){
		
		return defaultInn;
		
	}

}
