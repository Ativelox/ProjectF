package de.cormag.projectf.entities.creatures.humans.controlable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.Human;
import de.cormag.projectf.entities.properties.IHaveWeapon;
import de.cormag.projectf.entities.properties.ILively;
import de.cormag.projectf.entities.properties.offensive.IAttackable;
import de.cormag.projectf.entities.statics.weapons.AWeapon;
import de.cormag.projectf.entities.statics.weapons.IronSword;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.GameOverState;
import de.cormag.projectf.utils.time.GameTime;

public abstract class ControlableHuman extends Human implements ILively, IAttackable, IHaveWeapon {

	private static final long serialVersionUID = 1L;

	public static final int WALKING_ANIMATION_SPEED = 180;
	public static final int RUNNING_ANIMATION_SPEED = WALKING_ANIMATION_SPEED / 2;

	private float maxStamina;
	private float stamina;

	private int maxMagic;
	private int magic;

	private boolean leveledUp;
	private int level;
	private int experience;

	private float health;
	private float maxHealth;

	private AWeapon ironSword;

	private boolean sheathAble;

	private String lastMovement;

	private Handler handler;

	public ControlableHuman(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		this.handler = handler;

		stamina = 150;
		maxStamina = stamina;

		magic = 150;
		maxMagic = magic;

		health = 500;
		maxHealth = health;

		level = 1;
		experience = 0;

		sheathAble = true;

		leveledUp = false;

		ironSword = new IronSword(this, handler, getX(), getY(), IronSword.DEFAULT_IRON_SWORD_POWER);

		applyResources();
	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		updateSteadyAnimation();

		updateStatsIfLeveledUp();

		// checkWeaponSkillUsage();

		drawSword(handler.getKeyManager().space);

		checkStaminaUsage();

		checkMagicUsage();

		dieIfDead();

		tickAnimations();

	}

	@Override
	public void render(Graphics g, final GameTime gameTime, BufferedImage imageToDraw) {

		super.render(g, gameTime, imageToDraw);

		drawLevelUpIfApplicable(g);

	}

	private void dieIfDead() {

		if (this.health <= 0) {
			health = 0;
			handler.getGame().getStateManager().push(new GameOverState(handler));

		}
	}

	private void tickAnimations() {

		if (sprinting) {

			runningAnimDown.tick();
			runningAnimUp.tick();
			runningAnimLeft.tick();
			runningAnimRight.tick();

		} else {

			walkingAnimDown.tick();
			walkingAnimUp.tick();
			walkingAnimRight.tick();
			walkingAnimLeft.tick();

		}

	}

	private void updateStatsIfLeveledUp() {

		if (experience >= experienceNeeded() && !leveledUp) {

			leveledUp = true;

			level += 1;
			maxHealth += 37.1;
			maxStamina += 3.3;
			maxMagic += 3.3;

			stamina = maxStamina;

			experience = 0;
		}

	}

	private void checkMagicUsage() {
		if (magic <= 0) {
			magic = 0;

		}

	}

	private void checkStaminaUsage() {

		if (handler.getWorld().getEntityManager() != null) {
			if (handler.getKeyManager().shift
					&& !handler.getWorld().getEntityManager().contains(handler.getPlayer().getWeapon())
					&& (horizontalDirection != 0 || verticalDirection != 0)) {

				if (stamina == 0 || stamina < 0) {

					sprinting = false;
					stamina = 0;

				} else {

					sprinting = true;
					stamina -= 0.5;
				}

			} else {

				if (stamina == maxStamina || stamina > maxStamina) {

					sprinting = false;
					stamina = maxStamina;

				} else {

					sprinting = false;
					stamina += 0.7;

				}
			}
		}

	}

	private void drawLevelUpIfApplicable(Graphics g) {

		if (leveledUp) {

			g.setColor(Color.WHITE);

			g.setFont(Assets.OPTIMUS_PRINCEPS.deriveFont(25f));

			g.drawString("Level Up!", (int) (x) - 15, (int) (y) - 20);

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {

					leveledUp = false;

				}
			}, 2000);

		}

	}

	private void drawSword(boolean key) {

		if (key && handler.getWorld().getEntityManager() != null
				&& !handler.getWorld().getEntityManager().contains(ironSword) && sheathAble && stamina >= 50) {

			ironSword = new IronSword(this, handler, -200, -200, IronSword.DEFAULT_IRON_SWORD_POWER);

			handler.getWorld().getEntityManager().addEntity(ironSword);

			stamina -= getWeapon().getStaminaUsage();

			sheathAble = false;

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {

					sheathAble = true;

				}

			}, 500);

		}

	}

	public void updateSteadyAnimation() {

		if (verticalDirection == 1) {
			lastMovement = "down";

		}
		if (verticalDirection == -1) {
			lastMovement = "top";

		}
		if (horizontalDirection == 1) {
			lastMovement = "right";

		}
		if (horizontalDirection == -1) {
			lastMovement = "left";

		}

		if (lastMovement != null) {
			switch (lastMovement) {
			case "top":
				steadyAnimation = Assets.player_up[1];
				break;

			case "down":
				steadyAnimation = Assets.player_down[1];
				break;

			case "right":
				steadyAnimation = Assets.player_right[1];
				break;

			case "left":
				steadyAnimation = Assets.player_left[1];
				break;

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IHaveWeapon#getWeapon()
	 */
	@Override
	public AWeapon getWeapon() {
		return ironSword;
	}

	@Override
	public void setWeapon(AWeapon weapon) {
		ironSword = weapon;

	}

	public float getStamina() {

		return stamina;

	}

	public float getMaxStamina() {

		return maxStamina;
	}

	public void setStamina(float stamina) {

		this.stamina = stamina;

	}

	public int getMagic() {

		return magic;

	}

	public int getMaxMagic() {

		return maxMagic;

	}

	public int getLevel() {

		return this.level;

	}

	public int getExperience() {

		return this.experience;
	}

	public int experienceNeeded() {

		return level * 20;
	}

	public void awardExperiencePoints(int awardedExp) {

		this.experience += awardedExp;

	}

	@Override
	public float getLifepoints() {
		return health;

	}

	@Override
	public void setLifepoints(float amount) {
		if (amount > maxHealth) {
			health = maxHealth;
		} else {
			health = amount;
		}
	}

	@Override
	public float getMaxLifepoints() {
		return maxHealth;
	}

	public boolean isAlive() {
		if (health > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void changeLifepoints(final float amount) {
		if (health + amount > maxHealth) {
			health = maxHealth;
		} else {
			health += amount;
		}
	}

	@Override
	public void applyResources() {
		super.applyResources();

		walkingAnimDown = new Animation(WALKING_ANIMATION_SPEED, Assets.player_down);
		walkingAnimUp = new Animation(WALKING_ANIMATION_SPEED, Assets.player_up);
		walkingAnimLeft = new Animation(WALKING_ANIMATION_SPEED, Assets.player_left);
		walkingAnimRight = new Animation(WALKING_ANIMATION_SPEED, Assets.player_right);

		runningAnimDown = new Animation(RUNNING_ANIMATION_SPEED, Assets.player_down);
		runningAnimUp = new Animation(RUNNING_ANIMATION_SPEED, Assets.player_up);
		runningAnimLeft = new Animation(RUNNING_ANIMATION_SPEED, Assets.player_left);
		runningAnimRight = new Animation(RUNNING_ANIMATION_SPEED, Assets.player_right);

		steadyAnimation = Assets.player_down[1];

	}

}
