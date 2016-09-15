package de.cormag.projectf.entities.creatures.enemies.bosses;

import java.awt.Graphics;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.BossHealthBar;
import de.cormag.projectf.utils.time.GameTime;

public class VikingBoss extends Boss {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_MATTI_BOSS_WIDTH = 62, DEFAULT_MATTI_BOSS_HEIGHT = 75;

	private BossHealthBar bossHealthBar;

	public VikingBoss(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_MATTI_BOSS_WIDTH, DEFAULT_MATTI_BOSS_HEIGHT);

		this.handler = handler;
		this.health = 10;
		maxHealth = this.health;
		damaged = false;

		awardedExp = 100;

		sprinting = false;
		seenPlayer = false;
		damagedOnce = false;

		speed = 2;

		bossHealthBar = new BossHealthBar(this, handler);

		applyResources();

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, getCurrentAnimationFrame(Assets.mattiBoss_left[1], Assets.mattiBoss_right[1],
				Assets.mattiBoss_up[1], Assets.mattiBoss_down[1]));

	}

	@Override
	public void applyResources() {
		super.applyResources();

		walkingAnimDown = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.mattiBoss_down);
		walkingAnimUp = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.mattiBoss_up);
		walkingAnimLeft = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.mattiBoss_left);
		walkingAnimRight = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.mattiBoss_right);

		steadyAnimation = Assets.mattiBoss_down[1];

	}

	@Override
	public BossHealthBar getBossHealthBar() {

		return bossHealthBar;
	}

}
