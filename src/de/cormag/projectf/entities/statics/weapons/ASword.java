package de.cormag.projectf.entities.statics.weapons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.entities.properties.IHaveAnimations;
import de.cormag.projectf.entities.properties.IHaveWeapon;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public abstract class ASword extends AWeapon {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WEAPON_WIDTH = 32;
	public static final int DEFAULT_WEAPON_HEIGHT = 32;

	private final Entity mParent;

	private final IHaveAnimations mParentAsIHaveAnimations;

	protected transient BufferedImage steadyAnimation;

	protected boolean attack;
	protected boolean dispose;

	protected int staminaUsage;

	private Handler handler;

	private int tickcount;

	protected transient Animation attackAnimationRight, attackAnimationLeft, attackAnimationUp, attackAnimationDown;

	protected transient BufferedImage noAttackRight, noAttackLeft, noAttackUp, noAttackDown;

	protected int rightArmX, rightArmY, leftArmX, leftArmY, upperArmX, upperArmY, lowerArmX, lowerArmY;

	public static final int WEAPON_ANIMATION_SPEED_PER_FRAME = 16, WEAPON_IMAGEARRAY_LENGTH = 8;

	public static final int MILLISECONDS_TO_DISPOSE = WEAPON_ANIMATION_SPEED_PER_FRAME * WEAPON_IMAGEARRAY_LENGTH;

	public static final float TICKS_TO_DISPOSE = MILLISECONDS_TO_DISPOSE * 0.12f;

	public ASword(IHaveWeapon parent, Handler handler, float x, float y, int width, int height, float attackValue) {
		super(parent, handler, x, y, width, height, attackValue);

		getBounds().x = (Player.DEFAULT_CREATURE_WIDTH / 2) + (Player.DEFAULT_CREATURE_WIDTH / 4);
		getBounds().y = 0;
		getBounds().width = DEFAULT_WEAPON_WIDTH;
		getBounds().height = DEFAULT_WEAPON_HEIGHT * 2;

		if (!(parent instanceof IHaveAnimations && parent instanceof Entity)) {
			throw new IllegalArgumentException("the given object must implement IHaveAnimations and extend Entity");

		}

		mParent = (Entity) parent;

		mParentAsIHaveAnimations = (IHaveAnimations) mParent;

		this.handler = handler;

		tickcount = 0;

		attack = false;
		attackValue = 1;

		applyResources();

		// setWeaponDirectionEqualToParent();
		// updateCorrespondingParentArms();

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		if (tickcount >= Math.floor(TICKS_TO_DISPOSE)) {
			disposeWeapon();
			tickcount = 0;
			return;

		}

		setWeaponDirectionEqualToParent();
		updateCorrespondingParentArms();

		updateHitbox();

		attackAnimationRight.tick();
		attackAnimationLeft.tick();
		attackAnimationUp.tick();
		attackAnimationDown.tick();

		tickcount++;

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		update(gameTime);

		g.drawImage(this.getCurrentAnimationFrame(), (int) getX(), (int) getY(), null);

		super.render(g, gameTime);

	}

	protected void updateWeaponPosition(float parentArmX, float parentArmY) {
		setRelativeX(parentArmX);
		setRelativeY(parentArmY);

	}

	protected void updateCorrespondingParentArms() {

		rightArmX = (int) mParent.getRelativeX() + ((mParent.getWidth() / 2) + 8);
		rightArmY = (int) mParent.getRelativeY() - ((mParent.getHeight() / 4));

		leftArmX = (int) mParent.getRelativeX() - ((mParent.getWidth() / 2) + 8);
		leftArmY = (int) mParent.getRelativeY() - (mParent.getHeight() / 4);

		upperArmX = (int) mParent.getRelativeX() - ((mParent.getWidth() / 2));
		upperArmY = (int) mParent.getRelativeY() - ((mParent.getHeight() / 2) + 5);

		lowerArmX = (int) mParent.getRelativeX() - ((mParent.getWidth() / 2));
		lowerArmY = (int) mParent.getRelativeY() + ((mParent.getHeight()) - 15);

		if (mParentAsIHaveAnimations.getHorizontalDirection() > 0 || steadyAnimation.equals(noAttackRight)) {

			updateWeaponPosition(rightArmX, rightArmY);

		} else if (mParentAsIHaveAnimations.getHorizontalDirection() < 0 || steadyAnimation.equals(noAttackLeft)) {

			updateWeaponPosition(leftArmX, leftArmY);

		} else if (mParentAsIHaveAnimations.getVerticalDirection() < 0 || steadyAnimation.equals(noAttackUp)) {

			updateWeaponPosition(upperArmX, upperArmY);

		} else if (mParentAsIHaveAnimations.getVerticalDirection() > 0 || steadyAnimation.equals(noAttackDown)) {

			updateWeaponPosition(lowerArmX, lowerArmY);

		}

	}

	protected BufferedImage getCurrentAnimationFrame() {

		if (mParentAsIHaveAnimations.getHorizontalDirection() < 0 || steadyAnimation.equals(noAttackLeft)) {

			return attackAnimationLeft.getCurrentFrame();

		} else if (mParentAsIHaveAnimations.getHorizontalDirection() > 0 || steadyAnimation.equals(noAttackRight)) {

			return attackAnimationRight.getCurrentFrame();

		} else if (mParentAsIHaveAnimations.getVerticalDirection() < 0 || steadyAnimation.equals(noAttackUp)) {

			return attackAnimationUp.getCurrentFrame();

		} else if (mParentAsIHaveAnimations.getVerticalDirection() > 0 || steadyAnimation.equals(noAttackDown)) {

			return attackAnimationDown.getCurrentFrame();

		} else {

			return null;

		}
	}

	@Override
	public void updateHitbox() {

		if (mParentAsIHaveAnimations.getHorizontalDirection() > 0 || steadyAnimation.equals(noAttackRight)) {
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = 30;
			getBounds().width = DEFAULT_WEAPON_WIDTH;
			getBounds().height = DEFAULT_WEAPON_HEIGHT * 2;

		} else if (mParentAsIHaveAnimations.getHorizontalDirection() < 0 || steadyAnimation.equals(noAttackLeft)) {
			getBounds().x = 0;
			getBounds().y = 30;
			getBounds().width = DEFAULT_WEAPON_WIDTH;
			getBounds().height = DEFAULT_WEAPON_HEIGHT * 2;

		} else if (mParentAsIHaveAnimations.getVerticalDirection() < 0 || steadyAnimation.equals(noAttackUp)) {
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = 0;
			getBounds().height = DEFAULT_WEAPON_HEIGHT;
			getBounds().width = DEFAULT_WEAPON_WIDTH * 2;

		} else if (mParentAsIHaveAnimations.getVerticalDirection() > 0 || steadyAnimation.equals(noAttackDown)) {
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = DEFAULT_WEAPON_HEIGHT;
			getBounds().height = DEFAULT_WEAPON_HEIGHT;
			getBounds().width = DEFAULT_WEAPON_WIDTH * 2;

		}
	}

	protected void setWeaponDirectionEqualToParent() {

		if (mParentAsIHaveAnimations.getSteadyAnimation().equals(Assets.player_down[1])) {

			steadyAnimation = noAttackDown;

		} else if (mParentAsIHaveAnimations.getSteadyAnimation().equals(Assets.player_up[1])) {

			steadyAnimation = noAttackUp;

		} else if (mParentAsIHaveAnimations.getSteadyAnimation().equals(Assets.player_left[1])) {

			steadyAnimation = noAttackLeft;

		} else if (mParentAsIHaveAnimations.getSteadyAnimation().equals(Assets.player_right[1])) {

			steadyAnimation = noAttackRight;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.offensive.ICanAttack#
	 * getStaminaUsage()
	 */
	@Override
	public int getStaminaUsage() {
		return 50;
	}

	protected void disposeWeapon() {

		handler.getWorld().getEntityManager().removeEntity(this);

	}

	@Override
	public void applyResources() {
		super.applyResources();

		steadyAnimation = noAttackDown;

	}

}
