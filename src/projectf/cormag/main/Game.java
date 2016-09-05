package projectf.cormag.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Iterator;

import javax.swing.JOptionPane;

import projectf.cormag.display.Display;
import projectf.cormag.entities.Entity;
import projectf.cormag.gfx.Assets;
import projectf.cormag.gfx.GameCamera;
import projectf.cormag.input.KeyManager;
import projectf.cormag.saves.SaveGameManager;
import projectf.cormag.saves.SavedGame;
import projectf.cormag.states.GameState;
import projectf.cormag.states.MenuState;
import projectf.cormag.states.StateManager;
import projectf.cormag.worlds.World;

public class Game implements Runnable {

	private Display display;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	private long lastTime, now;
	private int currentFPS;

	// States
	private StateManager stateManager;

	// Input
	private KeyManager keyManager;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private Handler handler;
	
	public static final int WIDTH = 1000, HEIGHT = 700;

	public Game(String title) {
		this.title = title;
		keyManager = new KeyManager();
		currentFPS = 0;

	}

	private void init() {

		display = new Display(title, WIDTH, HEIGHT, this);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		handler = new Handler(this);
		
		stateManager = new StateManager();

		
		MenuState menuState = new MenuState(handler);

		stateManager.push(menuState);
	}

	private void reInit(SavedGame savedGame) {

		keyManager.resetKeys();
		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, savedGame.getXOffset(), savedGame.getYOffset());

		stateManager = new StateManager();

		GameState gameState = new GameState(handler, savedGame);

		stateManager.push(gameState);
		gameState.createHUD();

		Iterator<World> worldIterator = gameState.getWorldIterator();

		while (worldIterator.hasNext()) {

			World w = worldIterator.next();
			w.setHandler(handler);
			w.getEntityManager().setHandler(handler);
			w.applyResources();

			Iterator<Entity> entityIterator = w.getEntityManager().getEntities();

			while (entityIterator.hasNext()) {

				Entity e = entityIterator.next();
				e.setHandler(handler);

				e.applyResources();

			}
		}

	}

	private void tick() {

		keyManager.tick();

		if (keyManager.s && keyManager.ctrl) {
			String name = JOptionPane.showInputDialog("Enter name for the save file");
			resetFPSLock();
			if (name != null) {
				SaveGameManager.saveGame(name, this, handler);
			}
			keyManager.resetKeys();

		} else if (keyManager.l && keyManager.ctrl) {
			String name = JOptionPane.showInputDialog("Enter name of the file loaded");
			resetFPSLock();
			if (name != null) {
				SavedGame savedGame = SaveGameManager.loadSavedGame(name);
				reInit(savedGame);
			}
			keyManager.resetKeys();
			return;
		}

		stateManager.tick();

	}

	private void render() {

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		// Draw Here!

		stateManager.render(g);

		// End Drawing!
		bs.show();
		g.dispose();
	}

	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1_000_000_000 / fps;
		double ticksToProcess = 0;
		int ticksProcessed = 0;
		int timer = 0;
		lastTime = System.nanoTime();

		while (running) {

			now = System.nanoTime();
			ticksToProcess += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (ticksToProcess >= 1) {
				tick();
				ticksProcessed++;
				render();
				ticksToProcess--;
			}

			if (timer > 1_000_000_000) {

				currentFPS = ticksProcessed;
				ticksProcessed = 0;
				timer = 0;

			}
		}

		stop();

	}

	public void stopGame() {

		running = false;

	}

	public int getCurrentFPS() {

		return this.currentFPS;

	}

	private void resetFPSLock() {

		lastTime = System.nanoTime();
		now = System.nanoTime();

	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public Display getDisplay() {
		return display;
	}
	
	public Handler getHandler(){
		
		return handler;
		
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
