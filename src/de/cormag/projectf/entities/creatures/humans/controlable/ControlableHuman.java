package de.cormag.projectf.entities.creatures.humans.controlable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.Human;
import de.cormag.projectf.entities.statics.skills.weapons.LongRangeSwipe;
import de.cormag.projectf.entities.statics.weapons.IronSword;
import de.cormag.projectf.entities.statics.weapons.Weapon;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.GameOverState;

public abstract class ControlableHuman extends Human {

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

	private IronSword ironSword;

	private boolean sheathAble;

	private String lastMovement;

	private Handler handler;
	
	private LongRangeSwipe longRangeSwipe;

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

		sprinting = false;

		sheathAble = true;

		leveledUp = false;
		
		longRangeSwipe = new LongRangeSwipe(handler, x, y);

		applyResources();
	}

	@Override
	public void update() {
		super.update();

		updateSteadyAnimation();

		updateStatsIfLeveledUp();

		checkWeaponSkillUsage();
		
		drawSword(handler.getKeyManager().space);

		checkStaminaUsage();
		
		checkMagicUsage();

		calculatePlayerGettingDamage();

		dieIfDead();

		tickAnimations();

		getInput(handler.getKeyManager().up, handler.getKeyManager().down, handler.getKeyManager().left,
				handler.getKeyManager().right);
		move();

	}

	public void render(Graphics g, BufferedImage imageToDraw) {

		drawLevelUpIfApplicable(g);

		g.drawImage(imageToDraw, (int) (x - xOffset), (int) (y - yOffset), width, height, null);

		super.render(g);
	}
	

	private void checkWeaponSkillUsage() {

		if (handler.getKeyManager().one) {
			if(!handler.getWorld().getEntityManager().contains(longRangeSwipe)){
				drawSword(true);
				
				longRangeSwipe.setX(this.x);
				longRangeSwipe.setY(this.y);
				if(magic >= 30){
					magic -= 30;
					handler.getWorld().getEntityManager().addEntity(longRangeSwipe);
					
				}
			}

		}

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
	
	private void checkMagicUsage(){
		if(magic <= 0){
			magic = 0;

			
		}

	}

	private void checkStaminaUsage() {

		if (handler.getWorld().getEntityManager() != null) {
			if (handler.getKeyManager().shift
					&& !handler.getWorld().getEntityManager().contains(handler.getPlayer().getCurrentWeapon())
					&& (xMove != 0 || yMove != 0)) {

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

			g.drawString("Level Up!", (int) (x - xOffset) - 15, (int) (y - yOffset) - 20);

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

		if (key && !handler.getWorld().getEntityManager().contains(ironSword) && sheathAble
				&& stamina >= 50) {

			ironSword = new IronSword(handler, handler.getPlayer().getX(), handler.getPlayer().getY());

			handler.getWorld().getEntityManager().addEntity(ironSword);

			stamina -= getCurrentWeapon().getStaminaUsage();

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

		if (yMove > 0) {
			lastMovement = "down";

		}
		if (yMove < 0) {
			lastMovement = "top";

		}
		if (xMove > 0) {
			lastMovement = "right";

		}
		if (xMove < 0) {
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

	public Weapon getCurrentWeapon() {

		return ironSword;

	}

	public float getxMove() {

		return xMove;

	}

	public float getyMove() {

		return yMove;

	}

	public boolean getDamagedStatus() {
		return damaged;

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

	public void setX(float x) {

		this.x = x;

	}

	public void setY(float y) {

		this.y = y;

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
