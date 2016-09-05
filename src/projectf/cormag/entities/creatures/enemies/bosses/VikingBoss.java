package projectf.cormag.entities.creatures.enemies.bosses;

import java.awt.Graphics;

import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.Animation;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class VikingBoss extends Boss {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_MATTI_BOSS_WIDTH = 62, DEFAULT_MATTI_BOSS_HEIGHT = 75;

	public VikingBoss(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_MATTI_BOSS_WIDTH, DEFAULT_MATTI_BOSS_HEIGHT);

		this.handler = handler;
		this.health = 10;
		maxHealth = this.health;
		damaged = false;
		attackValue = 5;

		awardedExp = 100;

		name = "Banboozler";

		sprinting = false;
		seenPlayer = false;
		damagedOnce = false;

		speed = 2;

		applyResources();

	}

	@Override
	public void tick() {
		super.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g, Assets.mattiBoss_left[1], Assets.mattiBoss_right[1], Assets.mattiBoss_up[1],
				Assets.mattiBoss_down[1]);

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

}