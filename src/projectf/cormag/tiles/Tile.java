package projectf.cormag.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import projectf.cormag.tiles.teleport.SandTeleport;

public class Tile {

	// STATIC STUFF HERE

	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	public static Tile waterTile = new WaterTile(3);
	public static Tile woodenPlanketTile = new WoodenPlanketTile(4);
	public static Tile gravelTile = new GravelTile(5);
	public static Tile mediumWoodenTile = new MediumWoodenGround(6);
	public static Tile darkWoodenWall = new DarkWoodenWall(7);
	public static Tile sand = new SandTile(8);
	public static Tile solidSandStone = new SolidSandStone(9);
	public static Tile smoothSandStone = new SmoothSandStone(10);
	public static Tile sandStone = new SandStone(11);
	public static Tile grassSand = new GrassSand(12);
	public static Tile grassDirt = new GrassDirt(13);
	public static Tile grassStone = new GrassStone(14);
	public static Tile hardSand = new HardSand(15);
	public static Tile sandWithStone = new SandWithStone(16);
	public static Tile sandGrass = new SandGrass(17);
	public static Tile dirtGrass = new DirtGrass(18);
	public static Tile dirtStone = new DirtStone(19);
	public static Tile dirtSand = new DirtSand(20);
	public static Tile ice = new IceTile(21);
	public static Tile iceHoled = new IceHoled(22);
	public static Tile iceStone = new IceStone(23);
	public static Tile iceFrozenStone = new IceFrozenStone(24);
	
	public static Tile sandTeleport = new SandTeleport(200);

	// CLASS

	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;

		tiles[id] = this;
	}

	public void tick() {
		
		
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public boolean isSolid() {
		return false;
	}

	public int getId() {
		return id;
	}

}