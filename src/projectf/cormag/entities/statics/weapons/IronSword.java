package projectf.cormag.entities.statics.weapons;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.Animation;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class IronSword extends Weapon implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_IRON_SWORD_POWER = 5;

	public IronSword(Handler handler, float x, float y) {

		super(handler, x, y, DEFAULT_WEAPON_WIDTH, DEFAULT_WEAPON_HEIGHT);

		getBounds().x = (Player.DEFAULT_CREATURE_WIDTH / 2) + (Player.DEFAULT_CREATURE_WIDTH / 4);
		getBounds().y = 0;
		getBounds().width = DEFAULT_WEAPON_WIDTH;
		getBounds().height = DEFAULT_WEAPON_HEIGHT * 2;

		attack = false;
		this.handler = handler;

		attackValue = DEFAULT_IRON_SWORD_POWER;

		staminaUsage = 50;

		xOffset = handler.getGameCamera().getxOffset();
		yOffset = handler.getGameCamera().getyOffset();

		applyResources();

		checkPlayerDirection(handler);

	}

	public void tick() {
		super.tick();

		attackAnimationRight.tick();
		attackAnimationLeft.tick();
		attackAnimationUp.tick();
		attackAnimationDown.tick();

		updatePlayerArms(handler.getWorld().getEntityManager());

		updateCorrespondingPlayeArms(handler.getWorld().getEntityManager());

		disposeWeapon(handler);
	}

	public void render(Graphics g) {

		xOffset = handler.getGameCamera().getxOffset();
		yOffset = handler.getGameCamera().getyOffset();

		renderWeapon(g, handler.getWorld().getEntityManager(), xOffset, yOffset);

		renderHitBox(g);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				dispose = true;

			}

		}, 140);

	}

	@Override
	public void applyResources() {
		super.applyResources();

		attackAnimationRight = new Animation(16, Assets.attack_right);
		attackAnimationLeft = new Animation(16, Assets.attack_left);
		attackAnimationUp = new Animation(16, Assets.attack_up);
		attackAnimationDown = new Animation(16, Assets.attack_down);

		noAttackRight = Assets.default_sword_right;
		noAttackLeft = Assets.default_sword_left;
		noAttackUp = Assets.default_sword_up;
		noAttackDown = Assets.default_sword_down;

	}

}
