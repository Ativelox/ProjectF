package projectf.cormag.main;

import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.GameCamera;
import projectf.cormag.input.KeyManager;
import projectf.cormag.sound.BGMPlayer;
import projectf.cormag.worlds.LoadingScreen;
import projectf.cormag.worlds.TutorialDesert;
import projectf.cormag.worlds.TutorialFields;
import projectf.cormag.worlds.DefaultHouse;
import projectf.cormag.worlds.DefaultInn;
import projectf.cormag.worlds.World;

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
	
	

	public Handler(Game game) {
		this.game = game;
		bgmPlayer = new BGMPlayer(this);
		player = new Player(this, 200, 200);
		
		gameCamera = new GameCamera(this, 0, 0);
		
		tutorialFields = new TutorialFields(this, "TutorialFields.pflf");
		defaultHouse = new DefaultHouse(this, "defaultHouse.pflf");
		tutorialDesert = new TutorialDesert(this, "TutorialDesert.pflf");
		defaultInn = new DefaultInn(this, "defaultInn.pflf");
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
