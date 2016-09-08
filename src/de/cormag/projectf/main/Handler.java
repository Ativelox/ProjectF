package de.cormag.projectf.main;

import java.util.Iterator;
import java.util.LinkedList;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.creatures.enemies.bosses.Boss;
import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.gfx.GameCamera;
import de.cormag.projectf.input.KeyManager;
import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.worlds.LoadingScreen;
import de.cormag.projectf.worlds.World;
import de.cormag.projectf.worlds.buildings.DefaultHouse;
import de.cormag.projectf.worlds.buildings.DefaultInn;
import de.cormag.projectf.worlds.music.TutorialDesert;
import de.cormag.projectf.worlds.music.TutorialFields;
import de.cormag.projectf.worlds.music.respawnable.FieldOne;
import de.cormag.projectf.worlds.music.respawnable.RespawnableWorld;

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
	private FieldOne fieldOne;
	
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
		fieldOne = new FieldOne(this, "FieldOne.pflf");
		
		//TODO: need to add a world names FieldOne, has to be created new everytime u enter it for it to reload entities, solution still needs to be found
		
		worldList.add(tutorialFields);
		worldList.add(tutorialDesert);
		worldList.add(fieldOne);
	
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
		
		if(world instanceof RespawnableWorld){
			
			((RespawnableWorld) world).respawnEntities();
			
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
	
	public int getWidth(){
		return game.getWidth();
		
	}
	
	public int getHeight(){
		return game.getHeight();
		
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
	
	public FieldOne getFieldOne(){
		
		return fieldOne;
		
	}

}
