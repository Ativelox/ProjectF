package de.cormag.projectf.entities.creatures.enemies.monster;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.utils.time.GameTime;

public class Zombie extends Monster implements Serializable {

	private static final long serialVersionUID = 1L;

	public Zombie(Handler handler, float x, float y) {

		super(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT, Player.DEFAULT_CREATURE_WIDTH,
				Player.DEFAULT_CREATURE_HEIGHT);

		this.handler = handler;
		this.health = 5;
		maxHealth = this.health;
		damaged = false;

		speed = 200;

		awardedExp = 20;

		sprinting = false;

		applyResources();

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

	}

	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, getCurrentAnimationFrame(Assets.zombie_left[1], Assets.zombie_right[1],
				Assets.zombie_up[1], Assets.zombie_down[1]));
	}

	@Override
	public void applyResources() {
		super.applyResources();

		walkingAnimDown = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.zombie_down);
		walkingAnimUp = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.zombie_up);
		walkingAnimLeft = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.zombie_left);
		walkingAnimRight = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.zombie_right);

		steadyAnimation = Assets.zombie_down[1];

	}

}
