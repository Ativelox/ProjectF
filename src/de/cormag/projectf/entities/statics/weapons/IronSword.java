package de.cormag.projectf.entities.statics.weapons;

import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.properties.IHaveWeapon;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class IronSword extends ASword implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_IRON_SWORD_POWER = 20;

	public IronSword(IHaveWeapon parent, Handler handler, float x, float y, float attackValue) {
		super(parent, handler, x, y, DEFAULT_WEAPON_WIDTH, DEFAULT_WEAPON_HEIGHT, attackValue);

		attack = false;

		staminaUsage = 50;

		applyResources();
	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime);

	}

	@Override
	public void applyResources() {
		super.applyResources();

		attackAnimationRight = new Animation(ASword.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_right);
		attackAnimationLeft = new Animation(ASword.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_left);
		attackAnimationUp = new Animation(ASword.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_up);
		attackAnimationDown = new Animation(ASword.WEAPON_ANIMATION_SPEED_PER_FRAME, Assets.attack_down);

		noAttackRight = Assets.default_sword_right;
		noAttackLeft = Assets.default_sword_left;
		noAttackUp = Assets.default_sword_up;
		noAttackDown = Assets.default_sword_down;

	}

}
