package de.cormag.projectf.entities.statics.weapons;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class IronSword extends Weapon implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_IRON_SWORD_POWER = 5;

	public IronSword(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_WEAPON_WIDTH, DEFAULT_WEAPON_HEIGHT, DEFAULT_IRON_SWORD_POWER);

		attack = false;

		staminaUsage = 50;

		applyResources();
	}

	public void update() {
		super.update();

	}

	public void render(Graphics g) {
		super.render(g);

	}

	@Override
	public void applyResources() {
		super.applyResources();

		attackAnimationRight = new Animation(Weapon.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_right);
		attackAnimationLeft = new Animation(Weapon.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_left);
		attackAnimationUp = new Animation(Weapon.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_up);
		attackAnimationDown = new Animation(Weapon.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_down);

		noAttackRight = Assets.default_sword_right;
		noAttackLeft = Assets.default_sword_left;
		noAttackUp = Assets.default_sword_up;
		noAttackDown = Assets.default_sword_down;

	}

}
