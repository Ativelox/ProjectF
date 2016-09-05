package projectf.cormag.entities.creatures.enemies.monster;

import java.awt.Graphics;
import java.io.Serializable;

import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.Animation;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class Zombie extends Monster implements Serializable {

	private static final long serialVersionUID = 1L;

	public Zombie(Handler handler, float x, float y) {

		super(handler, x, y, Player.DEFAULT_CREATURE_WIDTH, Player.DEFAULT_CREATURE_HEIGHT);

		this.handler = handler;
		this.health = 5;
		maxHealth = this.health;
		damaged = false;
		attackValue = 1;

		speed = 3;

		awardedExp = 20;

		sprinting = false;

		applyResources();

	}

	@Override
	public void tick() {
		super.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g, Assets.zombie_left[1], Assets.zombie_down[1], Assets.zombie_up[1], Assets.zombie_down[1]);
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
