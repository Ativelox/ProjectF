package de.cormag.projectf.entities.creatures.enemies.bosses.music;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.BossHealthBar;
import de.cormag.projectf.utils.time.GameTime;

public class KingBoss extends BossWithMusic implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int KING_WIDTH = 70, KING_HEIGHT = 75;

	private BossHealthBar bossHealthBar;

	public KingBoss(Handler handler, float x, float y) {
		super(handler, x, y, KING_WIDTH, KING_HEIGHT, "boss/Let Me Hear.pfsf");

		this.handler = handler;
		this.health = 10;
		maxHealth = this.health;
		damaged = false;
		attackValue = 1;

		speed = Player.DEFAULT_SPEED / 2;

		awardedExp = 1000;

		sprinting = false;

		bossHealthBar = new BossHealthBar(this, handler);

		applyResources();

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, getCurrentAnimationFrame(Assets.king_left[1], Assets.king_right[1], Assets.king_up[1], Assets.king_down[1]));
	}

	@Override
	public void applyResources() {
		super.applyResources();

		walkingAnimDown = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_down);
		walkingAnimUp = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_up);
		walkingAnimLeft = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_left);
		walkingAnimRight = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_right);

		steadyAnimation = Assets.king_down[1];
	}

	@Override
	public BossHealthBar getBossHealthBar() {

		return bossHealthBar;
	}
}
