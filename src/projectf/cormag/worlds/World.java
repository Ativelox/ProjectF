package projectf.cormag.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import projectf.cormag.entities.EntityManager;
import projectf.cormag.entities.creatures.Creature;
import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.entities.statics.weapons.IronSword;
import projectf.cormag.main.Game;
import projectf.cormag.main.Handler;
import projectf.cormag.tiles.Tile;
import projectf.cormag.tiles.teleport.SandTeleport;
import projectf.cormag.tiles.teleport.TeleportTile;
import projectf.cormag.utils.Utils;

public abstract class World implements Serializable {

	private static final long serialVersionUID = 1L;

	protected EntityManager entityManager;
	protected Player player;
	protected transient Handler handler;
	protected int width, height;
	public IronSword ironSword;
	public boolean debug, debugActive;
	protected int[][] tiles;
	protected String path;
	
	protected boolean isRunning;
	
	protected boolean canChangeWorld;

	public World() {

		debugActive = false;
		debug = false;
		canChangeWorld = true;
		

	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;

		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.dirtTile;
		return t;
	}

	public void renderWorld(Graphics g, Handler handler) {

		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + Game.WIDTH) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + Game.HEIGHT) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
	}

	public void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);

		tiles = new int[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
			}
		}
	}
	
	public void renderTeleportTileHitbox(Graphics g){
		
		for (int y = 0; y < height; y++) {
			
			for (int x = 0; x < width; x++) {
				
				if(getTile(x, y) instanceof TeleportTile){
					g.setColor(Color.RED);
					Rectangle tempTeleportTileHitbox = new Rectangle((int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
							   (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT);
					
					g.drawRect(tempTeleportTileHitbox.x, tempTeleportTileHitbox.y, tempTeleportTileHitbox.width, tempTeleportTileHitbox.height);
					
				}
			}
		}

	}
	
	public Tile getCorrespondingTeleportTile(){
		
		if(!(handler.getWorld() instanceof LoadingScreen)){
		
			int playerTileX = (int) Math.ceil(((handler.getPlayer().getX() + (handler.getPlayer().getWidth() / 2)) / 64) - 1);
			int playerTileY = (int) Math.ceil(((handler.getPlayer().getY() + (handler.getPlayer().getHeight())) / 64) - 1);
			
			if(getTile(playerTileX, playerTileY) instanceof TeleportTile){
				
				return getTile(playerTileX, playerTileY);
				
			}
			return null;
		
		}
		return null;
	}
	
	public void goToTutorialDesert(){
		
		if(getCorrespondingTeleportTile() instanceof SandTeleport){
			
			isRunning = false;
			
			handler.setWorld(new LoadingScreen(handler.getTutorialDesert(), handler, 
					handler.getTutorialDesert().getComingFromFields().x, handler.getTutorialDesert().getComingFromFields().y, handler.getBGMPlayer()));
			
		}
		
	}
	
	public void goToTutorialFields(){
		
		if(getCorrespondingTeleportTile() instanceof SandTeleport){
			
			isRunning = false;
			
			handler.setWorld(new LoadingScreen(handler.getTutorialFields(), handler, 
					handler.getTutorialFields().getComingFromDesert().x, handler.getTutorialFields().getComingFromDesert().y, handler.getBGMPlayer()));
			
		}
		
	}

	public void tick(){
		
		

	}

	public void render(Graphics g){
		
		renderWorld(g, handler);
		renderTeleportTileHitbox(g);
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public boolean getCanChangeWorld() {

		return canChangeWorld;

	}

	public void setCanChangeWorld(boolean canChangeWorld) {

		this.canChangeWorld = canChangeWorld;

	}

	public void setHandler(Handler handler) {

		this.handler = handler;

	}

	public Point getPlayerCenter(Player player, Handler handler) {

		return new Point((int) ((player.getX() + Creature.DEFAULT_CREATURE_WIDTH / 2)),
				(int) ((player.getY() + Creature.DEFAULT_CREATURE_HEIGHT / 2)));

	}
	
	public boolean getIsRunning(){
		
		return isRunning;
		
	}
	
	public void applyResources(){
		
		
	}
	
	public void stopMusic(){
		
		
		
	}

}