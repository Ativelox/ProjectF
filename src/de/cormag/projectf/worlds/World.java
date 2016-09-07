package de.cormag.projectf.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Iterator;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.entities.statics.weapons.IronSword;
import de.cormag.projectf.main.Game;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.tiles.teleport.TeleportTile;
import de.cormag.projectf.utils.Utils;

public abstract class World implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Player player;
	protected transient Handler handler;
	protected int width, height;
	public IronSword ironSword;
	public boolean debug, debugActive;
	protected int[][] tiles;
	protected String path;

	public World() {

		debugActive = false;
		debug = false;

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
	
//	public void checkTilesOnScreen(){
//		
//		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
//		int xEnd = (int) Math.min(width,
//				(handler.getGameCamera().getxOffset() + Game.WIDTH) / Tile.TILEWIDTH + 1);
//		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
//		int yEnd = (int) Math.min(height,
//				(handler.getGameCamera().getyOffset() + Game.HEIGHT) / Tile.TILEHEIGHT + 1);
//
//		for(int y = 0; y < height; y++){
//			for(int x = 0; x < width; x++){
//				if((x >= xStart && x <= xEnd) && (y >= yStart && y <= yEnd)){
//					getTile(x, y).setOnScreen(true);		
//				}else{
//					getTile(x, y).setOnScreen(false);	
//				}
//			}
//		}
//	}

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
	
	protected void changeWorldIfDemanded(Point newWorldSpawn, Tile tileSteppedOn){
		
		if(getCorrespondingTeleportTile() != null && getCorrespondingTeleportTile().equals(tileSteppedOn)){
			
			Iterator <World> worlds = handler.getWorldList().iterator();
			
			while(worlds.hasNext()){
				World w = worlds.next();
				
				if(w.getPath() == ((TeleportTile)tileSteppedOn).getWorldToTeleportTo()){
					
					handler.getBGMPlayer().setLastMusicInWorld(handler.getBGMPlayer().getClip(), handler.getWorld());
					
					handler.setWorld(new LoadingScreen(w, handler, newWorldSpawn.x, newWorldSpawn.y, handler.getBGMPlayer()));
					
				}
				
				
			}
			
		}

	}
	
	public void tick(){};

	public void render(Graphics g){
		
		renderWorld(g, handler);
		renderTeleportTileHitbox(g);
		
	}
	
	public String getPath(){
		
		return path;
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public abstract EntityManager getEntityManager();

	public void setHandler(Handler handler) {

		this.handler = handler;

	}

	public Point getPlayerCenter(Player player, Handler handler) {

		return new Point((int) ((player.getX() + Creature.DEFAULT_CREATURE_WIDTH / 2)),
				(int) ((player.getY() + Creature.DEFAULT_CREATURE_HEIGHT / 2)));

	}
	
	public void applyResources(){
		
		
	}
	
	public void stopMusic(){
		
		
		
	}

}