package projectf.cormag.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

	private static final int WIDTH = 32, HEIGHT = 32;
	private static final int FOUR_TIMES_WIDTH = 128, TWO_TIMES_WIDTH = 64;
	private static final int FOUR_TIMES_HEIGHT = 128, TWO_TIMES_HEIGHT = 64;
	private static final int AVATAR_WIDTH = 96;
	private static final int AVATAR_HEIGHT = 96;
	private static final int DEMON_BOSS_WIDTH = 96, DEMON_BOSS_HEIGHT = 48;
	private static final int CHARACTERSPRITE_TOPPADDING = 126;

	private static final int DEFAULT_TILE_WIDTH = 128, DEFAULT_TILE_HEIGHT = 128;
	private static final int TILE_PADDING_WIDTH = 16, TILE_PADDING_HEIGHT = 17;

	public static BufferedImage dirt, grass, stone, tree, rock, water, woodenPlankets, gravel, mediumWoodenGround, sand,
			solidSandStone, sandStone, smoothSandStone, darkWoodenWall, grassDirt, grassSand, grassStone, hardSand, sandGrass, sandWithStone,
			dirtGrass, dirtSand, dirtStone, ice, iceFrozenStone, iceHoled, iceStone;
	
	public static BufferedImage background;
	public static BufferedImage female_NPC;
	public static BufferedImage classic_house;
	public static BufferedImage stoneHouseSemiFlat, stoneHouseSharp, stoneHouseFlat;
	public static BufferedImage hiFiSystem;
	public static BufferedImage piano;
	public static BufferedImage iron_sword;
	public static BufferedImage menuLayerBackground;
	public static BufferedImage avatarMenuFace;

	public static Font OPTIMUS_PRINCEPS;

	public static BufferedImage[] player_down, player_up, player_left, player_right, player_current;
	public static BufferedImage[] efis_down, efis_up, efis_left, efis_right, efis_current;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right, zombie_current;
	public static BufferedImage[] demonBoss_down, demonBoss_up, demonBoss_left, demonBoss_right, demonBoss_current;
	public static BufferedImage[] mattiBoss_down, mattiBoss_up, mattiBoss_left, mattiBoss_right, mattiBoss_current;
	public static BufferedImage[] dragonBoss_down, dragonBoss_up, dragonBoss_left, dragonBoss_right, dragonBoss_current;
	public static BufferedImage[] king_down, king_up, king_left, king_right, king_current;
	public static BufferedImage[] attack_right, attack_left, attack_up, attack_down;
	public static BufferedImage default_sword_right, default_sword_left, default_sword_up, default_sword_down;
	public static BufferedImage avatar_crazy_mouth_open, avatar_depressed, avatar_standard, avatar_sad, avatar_very_sad,
			avatar_crazy_mouth_shut, avatar_surprised_cry, avatar_sad_cry;

	public static void init() {
		

		try {
			OPTIMUS_PRINCEPS = Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResourceAsStream("/fonts/OptimusPrinceps.ttf"));

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(OPTIMUS_PRINCEPS);

		} catch (IOException | FontFormatException e) {

			e.printStackTrace();

		}

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("sheet.pfpf"));
		SpriteSheet anime_sprite = new SpriteSheet(ImageLoader.loadImage("anime_sprite.pfpf"));
		SpriteSheet classic_house_sprite = new SpriteSheet(ImageLoader.loadImage("classic_house.pfpf"));
		SpriteSheet sword_sprite = new SpriteSheet(ImageLoader.loadImage("iron_sword_sprite.pfpf"));
		SpriteSheet avatar_sprite = new SpriteSheet(ImageLoader.loadImage("avatar_faces.pfpf"));
		SpriteSheet enemy_sprite = new SpriteSheet(ImageLoader.loadImage("EnemySpriteSheet1.pfpf"));
		SpriteSheet terrain_sprite = new SpriteSheet(ImageLoader.loadImage("terrainSprite.pfpf"));
		SpriteSheet examp_boss = new SpriteSheet(ImageLoader.loadImage("BossExamp.pfpf"));
		SpriteSheet hiFiSystem_sheet = new SpriteSheet(ImageLoader.loadImage("HiFiSystem.pfpf"));
		SpriteSheet mattiBoss_sheet = new SpriteSheet(ImageLoader.loadImage("Matti.pfpf"));
		SpriteSheet dragonBoss_sheet = new SpriteSheet(ImageLoader.loadImage("dragonSprite.pfpf"));
		SpriteSheet king_sheet = new SpriteSheet(ImageLoader.loadImage("king.pfpf"));
		SpriteSheet terrain_celianna = new SpriteSheet(ImageLoader.loadImage("CeliannaSpriteSheet.png")); //TODO: change png to pfpf
		SpriteSheet characterSprites = new SpriteSheet(ImageLoader.loadImage("charactersprites.png")); //TODO: SAME ^
		SpriteSheet house_sheet = new SpriteSheet(ImageLoader.loadImage("Houses.png"));//TODO:SAME ^
		SpriteSheet modernage = new SpriteSheet(ImageLoader.loadImage("modernage.png")); //TODO:SAME ^

		background = ImageLoader.loadImage("background.pfpf");

		menuLayerBackground = ImageLoader.loadImage("MenuActionBackground.pfpf");
		avatarMenuFace = ImageLoader.loadImage("AvatarMenuFace.pfpf");

		player_down = new BufferedImage[3];
		player_up = new BufferedImage[3];
		player_left = new BufferedImage[3];
		player_right = new BufferedImage[3];
		player_current = new BufferedImage[3];
		
		efis_down = new BufferedImage[3];
		efis_up = new BufferedImage[3];
		efis_left = new BufferedImage[3];
		efis_right = new BufferedImage[3];
		efis_current = new BufferedImage[3];

		zombie_down = new BufferedImage[3];
		zombie_up = new BufferedImage[3];
		zombie_left = new BufferedImage[3];
		zombie_right = new BufferedImage[3];
		zombie_current = new BufferedImage[3];

		demonBoss_down = new BufferedImage[3];
		demonBoss_up = new BufferedImage[3];
		demonBoss_left = new BufferedImage[3];
		demonBoss_right = new BufferedImage[3];
		demonBoss_current = new BufferedImage[3];

		mattiBoss_down = new BufferedImage[3];
		mattiBoss_up = new BufferedImage[3];
		mattiBoss_left = new BufferedImage[3];
		mattiBoss_right = new BufferedImage[3];
		mattiBoss_current = new BufferedImage[3];

		dragonBoss_down = new BufferedImage[4];
		dragonBoss_up = new BufferedImage[4];
		dragonBoss_left = new BufferedImage[4];
		dragonBoss_right = new BufferedImage[4];
		dragonBoss_current = new BufferedImage[4];
		
		king_down = new BufferedImage[3];
		king_up = new BufferedImage[3];
		king_left = new BufferedImage[3];
		king_right = new BufferedImage[3];
		king_current = new BufferedImage[3];

		attack_right = new BufferedImage[7];

		attack_left = new BufferedImage[7];

		attack_up = new BufferedImage[7];

		attack_down = new BufferedImage[7];

		player_down[0] = anime_sprite.crop(0, 0, WIDTH, HEIGHT);
		player_down[1] = anime_sprite.crop(WIDTH, 0, WIDTH, HEIGHT);
		player_down[2] = anime_sprite.crop(WIDTH * 2, 0, WIDTH, HEIGHT);

		player_up[0] = anime_sprite.crop(0, HEIGHT * 3, WIDTH, HEIGHT);
		player_up[1] = anime_sprite.crop(WIDTH, HEIGHT * 3, WIDTH, HEIGHT);
		player_up[2] = anime_sprite.crop(WIDTH * 2, HEIGHT * 3, WIDTH, HEIGHT);

		player_left[0] = anime_sprite.crop(0, HEIGHT, WIDTH, HEIGHT);
		player_left[1] = anime_sprite.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
		player_left[2] = anime_sprite.crop(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);

		player_right[0] = anime_sprite.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
		player_right[1] = anime_sprite.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
		player_right[2] = anime_sprite.crop(WIDTH * 2, HEIGHT * 2, WIDTH, HEIGHT);

		player_current[0] = anime_sprite.crop(WIDTH, 0, WIDTH, HEIGHT);
		
		efis_down[0] = characterSprites.crop(0, CHARACTERSPRITE_TOPPADDING, WIDTH, HEIGHT);
		efis_down[1] = characterSprites.crop(WIDTH, CHARACTERSPRITE_TOPPADDING, WIDTH, HEIGHT);
		efis_down[2] = characterSprites.crop(WIDTH * 2, CHARACTERSPRITE_TOPPADDING, WIDTH, HEIGHT);
		
		efis_left[0] = characterSprites.crop(0, CHARACTERSPRITE_TOPPADDING + HEIGHT, WIDTH, HEIGHT);
		efis_left[1] = characterSprites.crop(WIDTH, CHARACTERSPRITE_TOPPADDING + HEIGHT, WIDTH, HEIGHT);
		efis_left[2] = characterSprites.crop(WIDTH * 2, CHARACTERSPRITE_TOPPADDING + HEIGHT, WIDTH, HEIGHT);
		
		efis_right[0] = characterSprites.crop(0, CHARACTERSPRITE_TOPPADDING + HEIGHT * 2, WIDTH, HEIGHT);
		efis_right[1] = characterSprites.crop(WIDTH, CHARACTERSPRITE_TOPPADDING + HEIGHT * 2, WIDTH, HEIGHT);
		efis_right[2] = characterSprites.crop(WIDTH * 2, CHARACTERSPRITE_TOPPADDING + HEIGHT * 2, WIDTH, HEIGHT);
		
		efis_up[0] = characterSprites.crop(0, CHARACTERSPRITE_TOPPADDING + HEIGHT * 3, WIDTH, HEIGHT);
		efis_up[1] = characterSprites.crop(WIDTH, CHARACTERSPRITE_TOPPADDING + HEIGHT * 3, WIDTH, HEIGHT);
		efis_up[2] = characterSprites.crop(WIDTH * 2, CHARACTERSPRITE_TOPPADDING + HEIGHT * 3, WIDTH, HEIGHT);
		
		efis_current[0] = characterSprites.crop(WIDTH, CHARACTERSPRITE_TOPPADDING, WIDTH, HEIGHT);

		zombie_down[0] = enemy_sprite.crop(0, 0, WIDTH, HEIGHT);
		zombie_down[1] = enemy_sprite.crop(WIDTH, 0, WIDTH, HEIGHT);
		zombie_down[2] = enemy_sprite.crop(WIDTH * 2, 0, WIDTH, HEIGHT);

		zombie_up[0] = enemy_sprite.crop(0, HEIGHT * 3, WIDTH, HEIGHT);
		zombie_up[1] = enemy_sprite.crop(WIDTH, HEIGHT * 3, WIDTH, HEIGHT);
		zombie_up[2] = enemy_sprite.crop(WIDTH, HEIGHT * 3, WIDTH, HEIGHT);

		zombie_left[0] = enemy_sprite.crop(0, HEIGHT, WIDTH, HEIGHT);
		zombie_left[1] = enemy_sprite.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
		zombie_left[2] = enemy_sprite.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);

		zombie_right[0] = enemy_sprite.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
		zombie_right[1] = enemy_sprite.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
		zombie_right[2] = enemy_sprite.crop(HEIGHT, HEIGHT * 2, WIDTH, HEIGHT);

		zombie_current[0] = enemy_sprite.crop(WIDTH, 0, WIDTH, HEIGHT);

		demonBoss_down[0] = examp_boss.crop(0, 0, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);
		demonBoss_down[1] = examp_boss.crop(DEMON_BOSS_WIDTH, 0, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);
		demonBoss_down[2] = examp_boss.crop(DEMON_BOSS_WIDTH * 2, 0, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);

		demonBoss_left[0] = examp_boss.crop(0, DEMON_BOSS_HEIGHT, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);
		demonBoss_left[1] = examp_boss.crop(DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);
		demonBoss_left[2] = examp_boss.crop(DEMON_BOSS_WIDTH * 2, DEMON_BOSS_HEIGHT, DEMON_BOSS_WIDTH,
				DEMON_BOSS_HEIGHT);

		demonBoss_right[0] = examp_boss.crop(0, DEMON_BOSS_HEIGHT * 2, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);
		demonBoss_right[1] = examp_boss.crop(DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT * 2, DEMON_BOSS_WIDTH,
				DEMON_BOSS_HEIGHT);
		demonBoss_right[2] = examp_boss.crop(DEMON_BOSS_WIDTH * 2, DEMON_BOSS_HEIGHT * 2, DEMON_BOSS_WIDTH,
				DEMON_BOSS_HEIGHT);

		demonBoss_up[0] = examp_boss.crop(0, DEMON_BOSS_HEIGHT * 3, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);
		demonBoss_up[1] = examp_boss.crop(DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT * 3, DEMON_BOSS_WIDTH, DEMON_BOSS_HEIGHT);
		demonBoss_up[2] = examp_boss.crop(DEMON_BOSS_WIDTH * 2, DEMON_BOSS_HEIGHT * 3, DEMON_BOSS_WIDTH,
				DEMON_BOSS_HEIGHT);

		demonBoss_current[0] = demonBoss_down[1];

		mattiBoss_down[0] = mattiBoss_sheet.crop(1, 2, 31, 37);
		mattiBoss_down[1] = mattiBoss_sheet.crop(33, 2, 31, 37);
		mattiBoss_down[2] = mattiBoss_sheet.crop(65, 2, 31, 37);

		mattiBoss_left[0] = mattiBoss_sheet.crop(1, 41, 31, 41);
		mattiBoss_left[1] = mattiBoss_sheet.crop(33, 41, 31, 41);
		mattiBoss_left[2] = mattiBoss_sheet.crop(65, 41, 31, 41);

		mattiBoss_right[0] = mattiBoss_sheet.crop(1, 83, 31, 42);
		mattiBoss_right[1] = mattiBoss_sheet.crop(33, 83, 31, 42);
		mattiBoss_right[2] = mattiBoss_sheet.crop(65, 83, 31, 42);

		mattiBoss_up[0] = mattiBoss_sheet.crop(1, 126, 31, 38);
		mattiBoss_up[1] = mattiBoss_sheet.crop(33, 126, 31, 38);
		mattiBoss_up[2] = mattiBoss_sheet.crop(65, 126, 31, 38);

		mattiBoss_current[0] = mattiBoss_down[1];

		dragonBoss_down[0] = dragonBoss_sheet.crop(0, 2, 96, 91);
		dragonBoss_down[1] = dragonBoss_sheet.crop(96, 2, 96, 91);
		dragonBoss_down[2] = dragonBoss_sheet.crop(192, 2, 96, 91);
		dragonBoss_down[3] = dragonBoss_sheet.crop(288, 2, 96, 91);

		dragonBoss_left[0] = dragonBoss_sheet.crop(0, 99, 97, 92);
		dragonBoss_left[1] = dragonBoss_sheet.crop(97, 99, 97, 92);
		dragonBoss_left[2] = dragonBoss_sheet.crop(194, 99, 96, 92);
		dragonBoss_left[3] = dragonBoss_sheet.crop(288, 99, 96, 92);

		dragonBoss_right[0] = dragonBoss_sheet.crop(0, 195, 94, 92);
		dragonBoss_right[1] = dragonBoss_sheet.crop(96, 195, 94, 92);
		dragonBoss_right[2] = dragonBoss_sheet.crop(191, 195, 94, 92);
		dragonBoss_right[3] = dragonBoss_sheet.crop(288, 195, 94, 92);

		dragonBoss_up[0] = dragonBoss_sheet.crop(0 + 3, 288, 91, 96);
		dragonBoss_up[1] = dragonBoss_sheet.crop(98 + 3, 288, 91, 96);
		dragonBoss_up[2] = dragonBoss_sheet.crop(191 + 3, 288, 91, 96);
		dragonBoss_up[3] = dragonBoss_sheet.crop(286 + 3, 288, 91, 96);

		dragonBoss_current[0] = dragonBoss_down[1];
		
		king_down[0] = king_sheet.crop(0, 1, 32, 35);
		king_down[1] = king_sheet.crop(32, 1, 32, 35);
		king_down[2] = king_sheet.crop(64, 1, 32, 35);
		
		king_left[0] = king_sheet.crop(0, 38, 32, 35);
		king_left[1] = king_sheet.crop(32, 38, 32, 35);
		king_left[2] = king_sheet.crop(64, 38, 32, 35);
		
		king_right[0] = king_sheet.crop(0, 74, 32, 35);
		king_right[1] = king_sheet.crop(32, 74, 32, 35);
		king_right[2] = king_sheet.crop(64, 74, 32, 35);
		
		king_up[0] = king_sheet.crop(0, 111, 32, 35);
		king_up[1] = king_sheet.crop(32, 111, 32, 35);
		king_up[2] = king_sheet.crop(64, 111, 32, 35);
		
		king_current[0] = king_down[1];

		attack_right[0] = sword_sprite.crop(TWO_TIMES_WIDTH, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_right[1] = sword_sprite.crop(TWO_TIMES_WIDTH * 2, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_right[2] = sword_sprite.crop(TWO_TIMES_WIDTH * 3, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_right[3] = sword_sprite.crop(TWO_TIMES_WIDTH * 4, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_right[4] = sword_sprite.crop(TWO_TIMES_WIDTH * 5, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_right[5] = sword_sprite.crop(TWO_TIMES_WIDTH * 6, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_right[6] = sword_sprite.crop(TWO_TIMES_WIDTH * 7, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);

		default_sword_right = sword_sprite.crop(0, 0, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);

		attack_left[0] = sword_sprite.crop(TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_left[1] = sword_sprite.crop(TWO_TIMES_WIDTH * 2, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_left[2] = sword_sprite.crop(TWO_TIMES_WIDTH * 3, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_left[3] = sword_sprite.crop(TWO_TIMES_WIDTH * 4, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_left[4] = sword_sprite.crop(TWO_TIMES_WIDTH * 5, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_left[5] = sword_sprite.crop(TWO_TIMES_WIDTH * 6, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);
		attack_left[6] = sword_sprite.crop(TWO_TIMES_WIDTH * 7, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);

		default_sword_left = sword_sprite.crop(0, FOUR_TIMES_HEIGHT, TWO_TIMES_WIDTH, FOUR_TIMES_HEIGHT);

		attack_up[0] = sword_sprite.crop(FOUR_TIMES_WIDTH, FOUR_TIMES_HEIGHT * 2, FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);
		attack_up[1] = sword_sprite.crop(FOUR_TIMES_WIDTH * 2, FOUR_TIMES_HEIGHT * 2, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_up[2] = sword_sprite.crop(FOUR_TIMES_WIDTH * 3, FOUR_TIMES_HEIGHT * 2, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_up[3] = sword_sprite.crop(0, FOUR_TIMES_HEIGHT * 2 + TWO_TIMES_HEIGHT, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_up[4] = sword_sprite.crop(FOUR_TIMES_WIDTH, FOUR_TIMES_HEIGHT * 2 + TWO_TIMES_HEIGHT, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_up[5] = sword_sprite.crop(FOUR_TIMES_WIDTH * 2, FOUR_TIMES_HEIGHT * 2 + TWO_TIMES_HEIGHT,
				FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);
		attack_up[6] = sword_sprite.crop(FOUR_TIMES_WIDTH * 3, FOUR_TIMES_HEIGHT * 2 + TWO_TIMES_HEIGHT,
				FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);

		default_sword_up = sword_sprite.crop(0, FOUR_TIMES_HEIGHT * 2, FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);

		attack_down[0] = sword_sprite.crop(FOUR_TIMES_WIDTH, FOUR_TIMES_HEIGHT * 3, FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);
		attack_down[1] = sword_sprite.crop(FOUR_TIMES_WIDTH * 2, FOUR_TIMES_HEIGHT * 3, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_down[2] = sword_sprite.crop(FOUR_TIMES_WIDTH * 3, FOUR_TIMES_HEIGHT * 3, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_down[3] = sword_sprite.crop(0, FOUR_TIMES_HEIGHT * 3 + TWO_TIMES_HEIGHT, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_down[4] = sword_sprite.crop(FOUR_TIMES_WIDTH, FOUR_TIMES_HEIGHT * 3 + TWO_TIMES_HEIGHT, FOUR_TIMES_WIDTH,
				TWO_TIMES_HEIGHT);
		attack_down[5] = sword_sprite.crop(FOUR_TIMES_WIDTH * 2, FOUR_TIMES_HEIGHT * 3 + TWO_TIMES_HEIGHT,
				FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);
		attack_down[6] = sword_sprite.crop(FOUR_TIMES_WIDTH * 3, FOUR_TIMES_HEIGHT * 3 + TWO_TIMES_HEIGHT,
				FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);

		default_sword_down = sword_sprite.crop(0, FOUR_TIMES_HEIGHT * 3, FOUR_TIMES_WIDTH, TWO_TIMES_HEIGHT);

		water = terrain_sprite.crop(TILE_PADDING_WIDTH, DEFAULT_TILE_HEIGHT * 8 + 8 + TILE_PADDING_HEIGHT,
				DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
		dirt = terrain_celianna.crop(0, 64 * 2, 64, 64);
				
		grass = terrain_celianna.crop(0, 0, 64, 64);
		
		stone = terrain_sprite.crop(TILE_PADDING_WIDTH, TILE_PADDING_HEIGHT, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
		
		gravel = terrain_sprite.crop(TILE_PADDING_WIDTH + DEFAULT_TILE_WIDTH + 1,
				TILE_PADDING_HEIGHT + DEFAULT_TILE_HEIGHT * 6 + 6, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
		
		woodenPlankets = terrain_sprite.crop(TILE_PADDING_WIDTH, TILE_PADDING_HEIGHT + DEFAULT_TILE_HEIGHT * 3 + 3,
				DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
		mediumWoodenGround = terrain_sprite.crop(TILE_PADDING_WIDTH + DEFAULT_TILE_WIDTH + 1,
				TILE_PADDING_HEIGHT + DEFAULT_TILE_HEIGHT + 1, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
		
		darkWoodenWall = terrain_sprite.crop(TILE_PADDING_WIDTH + DEFAULT_TILE_WIDTH + 1,
				TILE_PADDING_HEIGHT + DEFAULT_TILE_HEIGHT * 2 + 2, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);

		sand = terrain_celianna.crop(0, 64, 64, 64);

		solidSandStone = terrain_sprite.crop(TILE_PADDING_WIDTH + DEFAULT_TILE_WIDTH * 2 + 2,
				TILE_PADDING_HEIGHT + DEFAULT_TILE_HEIGHT * 9 + 9, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);

		smoothSandStone = terrain_sprite.crop(TILE_PADDING_WIDTH, TILE_PADDING_HEIGHT + DEFAULT_TILE_HEIGHT * 9 + 9,
				DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);

		sandStone = terrain_sprite.crop(TILE_PADDING_WIDTH + DEFAULT_TILE_WIDTH + 1,
				TILE_PADDING_HEIGHT + DEFAULT_TILE_HEIGHT * 9 + 9, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
		
		grassDirt = terrain_celianna.crop(64 * 2, 0, 64, 64);
		
		grassSand = terrain_celianna.crop(64, 0, 64, 64);
		
		grassStone = terrain_celianna.crop(64 * 3, 0, 64, 64);
		
		hardSand = terrain_celianna.crop(64, 64, 64, 64);
		
		sandWithStone = terrain_celianna.crop(64*2, 64, 64, 64);
		
		sandGrass = terrain_celianna.crop(64*3, 64, 64, 64);
		
		dirtGrass = terrain_celianna.crop(64, 64*2, 64, 64);
		
		dirtSand = terrain_celianna.crop(64*3, 64*2, 64, 64);
		
		dirtStone = terrain_celianna.crop(64*2, 64*2, 64, 64);
		
		ice = terrain_celianna.crop(0, 64*3, 64, 64);
		
		iceFrozenStone = terrain_celianna.crop(64*3 , 64*3, 64, 64);
		
		iceHoled = terrain_celianna.crop(64, 64*3, 64, 64);
		
		iceStone = terrain_celianna.crop(64 * 2, 64*3, 64, 64);
		
		stoneHouseSemiFlat = house_sheet.crop(11, 22, 86, 112);
		
		stoneHouseSharp = house_sheet.crop(123, 7, 86, 127);
		
		stoneHouseFlat = house_sheet.crop(238, 38, 80, 96);
		
		piano = modernage.crop(384, 169, 64, 75);

		tree = sheet.crop(0, 0, WIDTH, HEIGHT * 2);
		rock = sheet.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
		female_NPC = anime_sprite.crop(WIDTH * 4, 0, WIDTH, HEIGHT);
		classic_house = classic_house_sprite.crop(0, 0, 128, 128);
		hiFiSystem = hiFiSystem_sheet.crop(0, 0, 99, 56);
		iron_sword = sword_sprite.crop(0, 0, WIDTH, HEIGHT);

		avatar_crazy_mouth_open = avatar_sprite.crop(0, 0, AVATAR_WIDTH, AVATAR_HEIGHT);
		avatar_depressed = avatar_sprite.crop(AVATAR_WIDTH, 0, AVATAR_WIDTH, AVATAR_HEIGHT);
		avatar_standard = avatar_sprite.crop(AVATAR_WIDTH * 2, 0, AVATAR_WIDTH, AVATAR_HEIGHT);
		avatar_sad = avatar_sprite.crop(AVATAR_WIDTH * 3, 0, AVATAR_WIDTH, AVATAR_HEIGHT);
		avatar_very_sad = avatar_sprite.crop(0, AVATAR_HEIGHT, AVATAR_WIDTH, AVATAR_HEIGHT);
		avatar_crazy_mouth_shut = avatar_sprite.crop(AVATAR_WIDTH, AVATAR_HEIGHT, AVATAR_WIDTH, AVATAR_HEIGHT);
		avatar_surprised_cry = avatar_sprite.crop(AVATAR_WIDTH * 2, AVATAR_HEIGHT, AVATAR_WIDTH, AVATAR_HEIGHT);
		avatar_sad_cry = avatar_sprite.crop(AVATAR_WIDTH * 3, AVATAR_HEIGHT, AVATAR_WIDTH, AVATAR_HEIGHT);
	}

}