package de.cormag.projectf.entities.creatures.enemies.bosses;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.BossHealthBar;

public class DemonBoss extends Boss implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_DEMON_BOSS_WIDTH = 96, DEFAULT_DEMON_BOSS_HEIGHT = 48;
	
	private BossHealthBar bossHealthBar;

	public DemonBoss(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_DEMON_BOSS_WIDTH * 2 + 40, DEFAULT_CREATURE_HEIGHT * 2);
		
		this.handler = handler;
		this.health = 20;
		maxHealth = this.health;
		damaged = false;
		attackValue = 50;

		awardedExp = 20;

		sprinting = false;
		
		bossHealthBar = new BossHealthBar(this, handler);

		applyResources();

	}

	public void tick() {
		super.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g, Assets.demonBoss_left[1], Assets.demonBoss_right[1], Assets.demonBoss_up[1],
				Assets.demonBoss_down[1]);

	}

	@Override
	protected boolean collisionWithTile(int x, int y) {
		return false;
	}

	@Override
	public void applyResources() {
		super.applyResources();

		walkingAnimDown = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.demonBoss_down);
		walkingAnimUp = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.demonBoss_up);
		walkingAnimLeft = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.demonBoss_left);
		walkingAnimRight = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.demonBoss_right);

		steadyAnimation = Assets.demonBoss_down[1];

	}

	@Override
	public BossHealthBar getBossHealthBar() {
		
		return bossHealthBar;
	}

}
