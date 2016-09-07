package de.cormag.projectf.entities.creatures.enemies.bosses.music;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.BossHealthBar;

public class DragonBoss extends BossWithMusic implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_DRAGON_BOSS_WIDTH = 250, DEFAULT_DRAGON_BOSS_HEIGHT = 250;

	private BossHealthBar bossHealthBar;

	public DragonBoss(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_DRAGON_BOSS_WIDTH, DEFAULT_DRAGON_BOSS_HEIGHT, "boss/Otherworld(WIP).pfsf");

		this.handler = handler;
		this.health = 20;
		maxHealth = this.health;
		damaged = false;
		attackValue = 20;

		speed = Player.DEFAULT_SPEED - 1f;

		awardedExp = 1000;
		
		sprinting = false;
		
		bossHealthBar = new BossHealthBar(this, handler);

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

	@Override
	public BossHealthBar getBossHealthBar() {
		
		return bossHealthBar;
	}





}