package de.cormag.projectf.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Iterator;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.properties.IUpdateable;
import de.cormag.projectf.entities.statics.FenceXBottomAlign;
import de.cormag.projectf.entities.statics.FenceXTopAlign;
import de.cormag.projectf.entities.statics.FenceYLeftAlign;
import de.cormag.projectf.entities.statics.FenceYRightAlign;
import de.cormag.projectf.entities.statics.VerdurousGreenTree;
import de.cormag.projectf.entities.statics.nocollision.GrassBushLarge;
import de.cormag.projectf.entities.statics.weapons.IronSword;
import de.cormag.projectf.main.Game;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.WorldName;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.tiles.teleport.TeleportTile;
import de.cormag.projectf.utils.Utils;
import de.cormag.projectf.utils.time.GameTime;

public abstract class World implements Serializable, IUpdateable, IRenderable {

	private static final long serialVersionUID = 1L;

	protected Player player;
	protected transient Handler handler;
	protected int width, height;
	public IronSword ironSword;
	public boolean debug, debugActive;
	protected int[][] tiles;
	protected String path;
	protected boolean worldNameRendered;

	private WorldName worldName;

	public World(String name) {

		debugActive = false;
		debug = false;
		worldNameRendered = false;

		worldName = new WorldName(name);

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
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + Game.WIDTH) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + Game.HEIGHT) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
	}

	protected void createGrassBushField(EntityManager entityManager, int xStart, int yStart, int width, int height) {

		EntityManager worldToCreateFieldTo = entityManager;

		for (int i = xStart; i < width; i++) {
			for (int j = yStart; j < height; j++) {
				worldToCreateFieldTo.addEntity(new GrassBushLarge(handler, i, j));

			}
		}
	}

	protected void createGreenWoods(EntityManager entityManager, int xStart, int yStart, int width, int height) {

		EntityManager worldToCreateWoodsTo = entityManager;

		for (int i = xStart; i < width; i++) {
			for (int j = yStart; j < height; j++) {
				worldToCreateWoodsTo.addEntity(new VerdurousGreenTree(handler, i, j));

			}
		}
	}

	protected void createAdditionalYBoundaryLeftAlign(EntityManager entityManager, int yTileToStartFrom,
			int yTileToEndAt, int xTileToStartFrom, int[] ySpaces) {

		EntityManager worldToCreateBoundaryTo = entityManager;

		boolean createSpace = false;

		for (int i = yTileToStartFrom - 2; i < yTileToEndAt + 1; i++) {

			if (ySpaces != null) {
				for (int j = 0; j < ySpaces.length; j++) {
					if (ySpaces[j] == i) {
						createSpace = true;

					}
				}
			}

			if (!createSpace) {
				worldToCreateBoundaryTo.addEntity(new FenceYLeftAlign(handler, xTileToStartFrom + 1, i));

			}
		}
	}

	protected void createAdditionalYBoundaryRightAlign(EntityManager entityManager, int yTileToStartFrom,
			int yTileToEndAt, int xTileToStartFrom, int[] ySpaces) {

		EntityManager worldToCreateBoundaryTo = entityManager;

		boolean createSpace = false;

		for (int i = yTileToStartFrom - 2; i < yTileToEndAt + 1; i++) {

			if (ySpaces != null) {
				for (int j = 0; j < ySpaces.length; j++) {
					if (ySpaces[j] == i) {
						createSpace = true;

					}
				}
			}

			if (!createSpace) {
				worldToCreateBoundaryTo.addEntity(new FenceYRightAlign(handler, xTileToStartFrom, i));

			}
		}
	}

	protected void createAdditionalXBoundaryTopAlign(EntityManager entityManager, int xTileToStartFrom,
			int xTileToEndAt, int yTileToStartFrom, int[] xSpaces) {

		EntityManager worldToCreateBoundaryTo = entityManager;

		boolean createSpace = false;

		for (int i = xTileToStartFrom; i <= xTileToEndAt - 1; i++) {

			if (xSpaces != null) {
				for (int j = 0; j < xSpaces.length; j++) {
					if (xSpaces[j] == i) {
						createSpace = true;

					}
				}
			}

			if (!createSpace) {
				worldToCreateBoundaryTo.addEntity(new FenceXTopAlign(handler, i, yTileToStartFrom - 1));
			}

			createSpace = false;
		}

	}

	protected void createAdditionalXBoundaryBottomAlign(EntityManager entityManager, int xTileToStartFrom,
			int xTileToEndAt, int yTileToStartFrom, int[] xSpaces) {

		EntityManager worldToCreateBoundaryTo = entityManager;

		boolean createSpace = false;

		for (int i = xTileToStartFrom; i <= xTileToEndAt - 1; i++) {

			if (xSpaces != null) {
				for (int j = 0; j < xSpaces.length; j++) {
					if (xSpaces[j] == i) {
						createSpace = true;

					}
				}
			}

			if (!createSpace) {
				worldToCreateBoundaryTo.addEntity(new FenceXBottomAlign(handler, i, yTileToStartFrom - 1));
			}

			createSpace = false;
		}

	}

	protected void createWorldBoundary(EntityManager entityManager) {

		EntityManager worldToCreateBoundaryTo = entityManager;

		for (int i = 0; i < height; i++) {

			worldToCreateBoundaryTo.addEntity(new FenceYLeftAlign(handler, width, i));

			worldToCreateBoundaryTo.addEntity(new FenceYRightAlign(handler, 0, i));

		}

		for (int i = 1; i <= width - 2; i++) {

			if (!(getTile(i, 0) instanceof TeleportTile)) {
				worldToCreateBoundaryTo.addEntity(new FenceXTopAlign(handler, i, 0));

			}

			if (!(getTile(i, height - 1) instanceof TeleportTile)) {
				worldToCreateBoundaryTo.addEntity(new FenceXBottomAlign(handler, i, height - 1));

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

	public void renderTeleportTileHitbox(Graphics g) {

		for (int y = 0; y < height; y++) {

			for (int x = 0; x < width; x++) {

				if (getTile(x, y) instanceof TeleportTile) {
					g.setColor(Color.RED);
					Rectangle tempTeleportTileHitbox = new Rectangle(
							(int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
							(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH,
							Tile.TILEHEIGHT);

					g.drawRect(tempTeleportTileHitbox.x, tempTeleportTileHitbox.y, tempTeleportTileHitbox.width,
							tempTeleportTileHitbox.height);

				}
			}
		}

	}

	public Tile getCorrespondingTeleportTile() {

		if (!(handler.getWorld() instanceof LoadingScreen)) {

			int playerTileX = (int) Math
					.ceil(((handler.getPlayer().getRelativeX() + (handler.getPlayer().getWidth() / 2)) / 64) - 1);
			int playerTileY = (int) Math
					.ceil(((handler.getPlayer().getRelativeY() + (handler.getPlayer().getHeight())) / 64) - 1);

			if (getTile(playerTileX, playerTileY) instanceof TeleportTile) {

				return getTile(playerTileX, playerTileY);

			}
			return null;

		}
		return null;
	}

	protected void changeWorldIfDemanded(Point newWorldSpawn, Tile tileSteppedOn) {

		if (getCorrespondingTeleportTile() != null && getCorrespondingTeleportTile().equals(tileSteppedOn)) {

			Iterator<World> worlds = handler.getWorldList().iterator();

			while (worlds.hasNext()) {
				World w = worlds.next();

				if (w.getPath() == ((TeleportTile) tileSteppedOn).getWorldToTeleportTo()) {

					handler.getBGMPlayer().setLastMusicInWorld(handler.getBGMPlayer().getClip(), handler.getWorld());

					handler.setWorld(
							new LoadingScreen(w, handler, newWorldSpawn.x, newWorldSpawn.y, handler.getBGMPlayer()));

				}

			}

		}

	}

	/*
	 * (non-Javadoc)
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update(de.cormag.projectf.utils.time.GameTime)
	 */
	@Override
	public void update(final GameTime gameTime) {
		
	}

	/*
	 * (non-Javadoc)
	 * @see de.cormag.projectf.entities.properties.IRenderable#render(java.awt.Graphics, de.cormag.projectf.utils.time.GameTime)
	 */
	@Override
	public void render(final Graphics g, final GameTime gameTime) {

		renderWorld(g, handler);
		renderTeleportTileHitbox(g);

		if (!worldNameRendered) {

			handler.getGame().getStateManager().getGameState().getHUDState().addHUDElement(worldName);

			worldNameRendered = true;

		}

	}

	public String getPath() {

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

	public void setWorldNameRenderedFalse() {

		this.worldNameRendered = false;

	}

	public WorldName getWorldNameHUDElement() {

		return worldName;

	}

	public void applyResources() {

	}

	public void stopMusic() {

	}

}