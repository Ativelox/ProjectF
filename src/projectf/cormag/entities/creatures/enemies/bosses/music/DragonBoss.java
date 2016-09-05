package projectf.cormag.entities.creatures.enemies.bosses.music;

import java.awt.Graphics;
import java.io.Serializable;

import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.Animation;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class DragonBoss extends BossWithMusic implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_DRAGON_BOSS_WIDTH = 250, DEFAULT_DRAGON_BOSS_HEIGHT = 250;


	public DragonBoss(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_DRAGON_BOSS_WIDTH, DEFAULT_DRAGON_BOSS_HEIGHT, "Otherworld(WIP).pfsf");

		this.handler = handler;
		this.health = 800;
		maxHealth = this.health;
		damaged = false;
		attackValue = 20;

		speed = Player.DEFAULT_SPEED - 1f;

		awardedExp = 1000;
		
		name = "Lul";

		sprinting = false;

		applyResources();

	}

	public void tick() {
		super.tick();

	}

	@Override 
	public void render(Graphics g) {
		super.render(g, Assets.dragonBoss_left[1], Assets.dragonBoss_right[1], Assets.dragonBoss_up[1],
				Assets.dragonBoss_down[1]);
	}

	@Override
	public void applyResources() {
		super.applyResources();

		walkingAnimDown = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.dragonBoss_down);
		walkingAnimUp = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.dragonBoss_up);
		walkingAnimLeft = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.dragonBoss_left);
		walkingAnimRight = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.dragonBoss_right);


		steadyAnimation = Assets.dragonBoss_down[2];

	}





}
