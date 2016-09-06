package de.cormag.projectf.entities.creatures.humans;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.talkable.TalkableHuman;
import de.cormag.projectf.entities.statics.weapons.IronSword;
import de.cormag.projectf.entities.statics.weapons.Weapon;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.GameOverState;
import de.cormag.projectf.states.InventoryState;

public class Player extends Human implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int WALKING_ANIMATION_SPEED = 180;
	public static final int RUNNING_ANIMATION_SPEED = WALKING_ANIMATION_SPEED / 2;

	private float maxStamina;
	private float stamina;

	private int maxMagic;
	private int magic;

	private transient boolean inventory;
	private boolean leveledUp;
	private int level;
	private int experience;
	
	private float lastX, lastY;
	private float lastXOffset, lastYOffset;

	private IronSword ironSword;

	private boolean sheathAble;
	
	private TalkableHuman lastEncountered;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		stamina = 150;
		maxStamina = stamina;

		magic = 2;
		maxMagic = magic;

		health = 15;
		maxHealth = health;

		xOffset = 0;
		yOffset = 0;

		level = 1;
		experience = 0;

		sprinting = false;
		inventory = false;

		sheathAble = true;

		leveledUp = false;

		applyResources();

	}

	@Override
	public void tick() {
		super.tick();
		
		handler.getGameCamera().centerOnEntity(this);
		
		talk(false);
		
		if (experience >= experienceNeeded() && !leveledUp) {

			leveledUp = true;

			level += 1;
			maxHealth += 5;
			health = maxHealth;
			// maxHealth += 1;
			// maxStamina += 20;
			// maxMagic += 3;

			experience = 0;
		}

		drawSword(handler.getWorld().getEntityManager(), handler, this);
		
		if(handler.getWorld().getEntityManager() != null){
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

		if (handler.getKeyManager().escape && !inventory) {

			handler.getGame().getStateManager().push(new InventoryState(handler, this));

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {

					inventory = true;
				}

			}, 333);
		}

		if (this.health <= 0) {
			
			health = 0;
			handler.getGame().getStateManager().push(new GameOverState(handler));
			
		}

		calculatePlayerGettingDamage(xOffset, yOffset, Creature.DEFAULT_CREATURE_WIDTH);

		// Animations
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
		// Movement

		getInput(handler.getKeyManager().up, handler.getKeyManager().down, handler.getKeyManager().left,
				handler.getKeyManager().right);
		move();
	
	}

	@Override
	public void render(Graphics g) {
		
		renderTalkNotification(g);
		
		if (leveledUp) {

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

		debug(g, xOffset, yOffset, handler, this);

		g.setColor(Color.black);

		g.drawImage(
				getCurrentAnimationFrame(Assets.player_left[1], Assets.player_right[1], Assets.player_up[1],
						Assets.player_down[1]),
				(int) (x - xOffset), (int) (y - yOffset), width, height, null);
		
		super.render(g);
	}

	private void debug(Graphics g, float xOffset, float yOffset, Handler handler, Player player) {

		if (handler.getWorld().debug) {

			g.setColor(new Color(0, 0, 0, 80));
			g.fillRect(750, 0, 250, 100);
			g.setFont(new Font(Font.SANS_SERIF, 3, 20));
			g.setColor(Color.white);
			g.drawString("Player Debug (G)", 800, 20);
			g.setFont(new Font(Font.DIALOG_INPUT, 1, 15));
			g.drawString("Invincible: " + player.getDamagedStatus(), 780, 50);
			g.drawString("Sprinting: " + sprinting, 780, 80);

		}
	}

	private void drawSword(EntityManager entityManager, Handler handler, Player player) {
		
		if(entityManager != null){
	
			if (handler.getKeyManager().space && !entityManager.contains(ironSword) && sheathAble && stamina >= 50) {
	
				ironSword = new IronSword(handler, player.getX(), player.getY());
	
				entityManager.addEntity(ironSword);
	
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

	}
	
	public void talk(boolean finishedTalking){
		
		if((returnNearStandingTalkableHuman() != null) && (returnNearStandingTalkableHuman().getProperCollisionRectangle().intersects(this.getNearbyRectangle()))){

			lastEncountered = returnNearStandingTalkableHuman();
			
			returnNearStandingTalkableHuman().talk(finishedTalking, returnNearStandingTalkableHuman());
			
		}else if(lastEncountered != null){
			
			lastEncountered.removeSpeechBubbleIfOOR(true);
			
		}
	}
	
	public void renderTalkNotification(Graphics g){
		
		if(returnNearStandingTalkableHuman() != null && returnNearStandingTalkableHuman().getProperCollisionRectangle().intersects(this.getNearbyRectangle())){
			
			returnNearStandingTalkableHuman().renderTalkNotification(g);
			
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

	public boolean getInventoryStatus() {

		return inventory;

	}

	public void setInventoryStatus(boolean inventory) {

		this.inventory = inventory;

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
	public void setX(float x){
		
		if(handler.getGameCamera() != null){
			lastXOffset = handler.getGameCamera().getxOffset();
		}
		
		lastX = this.x;
		
		this.x = x;
		
	}
	
	@Override
	public void setY(float y){
		
		if(handler.getGameCamera()!= null){
			lastYOffset = handler.getGameCamera().getyOffset();
		}
		
		lastY = this.y;
		
		this.y = y;
		
	}
	
	public float getLastX(){
		
		return lastX;
		
	}
	
	public float getLastY(){
		
		return lastY;
		
	}
	
	public float getLastXOffset(){
		
		return lastXOffset;
		
	}
	
	public float getLastYOffset(){
		
		return lastYOffset;
		
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