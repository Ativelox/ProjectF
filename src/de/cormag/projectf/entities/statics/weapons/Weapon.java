package de.cormag.projectf.entities.statics.weapons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.gfx.Animation;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public abstract class Weapon extends StaticEntity {

	private static final long serialVersionUID = 1L;

	protected static final int DEFAULT_WEAPON_WIDTH = 32;
	protected static final int DEFAULT_WEAPON_HEIGHT = 32;

	protected transient BufferedImage steadyAnimation;

	protected boolean attack;
	protected boolean dispose;

	protected int attackValue;
	protected int staminaUsage;
	
	private Handler handler;
	
	private int tickcount;

	protected transient Animation attackAnimationRight, attackAnimationLeft, attackAnimationUp, attackAnimationDown;

	protected transient BufferedImage noAttackRight, noAttackLeft, noAttackUp, noAttackDown;

	protected int rightArmX, rightArmY, leftArmX, leftArmY, upperArmX, upperArmY, lowerArmX, lowerArmY;
	
	public static final int WEAPON_ANIMATION_SPEED_PER_FRAME = 16, WEAPON_IMAGEARRAY_LENGTH = 8;
	
	public static final int MILLISECONDS_TO_DISPOSE = WEAPON_ANIMATION_SPEED_PER_FRAME * WEAPON_IMAGEARRAY_LENGTH;
	
	public static final float TICKS_TO_DISPOSE = MILLISECONDS_TO_DISPOSE * 0.12f;

	public Weapon(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		getBounds().x = (Player.DEFAULT_CREATURE_WIDTH / 2) + (Player.DEFAULT_CREATURE_WIDTH / 4);
		getBounds().y = 0;
		getBounds().width = DEFAULT_WEAPON_WIDTH;
		getBounds().height = DEFAULT_WEAPON_HEIGHT * 2;
		
		this.handler = handler;
		
		tickcount = 0;

		attack = false;
		attackValue = 1;

		applyResources();
		
		setWeaponDirectionEqualToPlayer();
		updateCorrespondingPlayerArms();
		

	}
	
	public void tick(){
		super.tick();
		
		if(tickcount >= Math.floor(TICKS_TO_DISPOSE)){
			disposeWeapon();
			tickcount = 0;
			return;
			
		}
		
		setWeaponDirectionEqualToPlayer();
		updateCorrespondingPlayerArms();
		
		updateHitbox();
		
		attackAnimationRight.tick();
		attackAnimationLeft.tick();
		attackAnimationUp.tick();
		attackAnimationDown.tick();

		tickcount++;
	
		
	}
	
	public void render(Graphics g){
		tick();
		g.drawImage(this.getCurrentAnimationFrame(), (int) (x - xOffset), (int) (y - yOffset), null);
		renderHitBox(g);
		
	}
		

	protected void updateWeaponPosition(float playerArmX, float playerArmY) {
		setX(playerArmX);
		setY(playerArmY);

	}

	protected void updateCorrespondingPlayerArms() {
		
		rightArmX = (int) handler.getPlayer().getX() + ((Creature.DEFAULT_CREATURE_WIDTH / 2) + 8);
		rightArmY = (int) handler.getPlayer().getY() - ((Creature.DEFAULT_CREATURE_HEIGHT / 4));

		leftArmX = (int) handler.getPlayer().getX() - ((Creature.DEFAULT_CREATURE_WIDTH / 2) + 8);
		leftArmY = (int) handler.getPlayer().getY() - (Creature.DEFAULT_CREATURE_HEIGHT / 4);

		upperArmX = (int) handler.getPlayer().getX() - ((Creature.DEFAULT_CREATURE_WIDTH / 2));
		upperArmY = (int) handler.getPlayer().getY() - ((Creature.DEFAULT_CREATURE_HEIGHT / 2) + 5);

		lowerArmX = (int) handler.getPlayer().getX() - ((Creature.DEFAULT_CREATURE_WIDTH / 2));
		lowerArmY = (int) handler.getPlayer().getY() + ((Creature.DEFAULT_CREATURE_HEIGHT) - 15);
	
		if (handler.getPlayer().getxMove() > 0 || steadyAnimation.equals(noAttackRight)) {

			updateWeaponPosition(rightArmX, rightArmY);

		} else if (handler.getPlayer().getxMove() < 0 || steadyAnimation.equals(noAttackLeft)) {

			updateWeaponPosition(leftArmX, leftArmY);

		} else if (handler.getPlayer().getyMove() < 0 || steadyAnimation.equals(noAttackUp)) {

			updateWeaponPosition(upperArmX, upperArmY);

		} else if (handler.getPlayer().getyMove() > 0 || steadyAnimation.equals(noAttackDown)) {

			updateWeaponPosition(lowerArmX, lowerArmY);

		}
		

	}

	protected BufferedImage getCurrentAnimationFrame() {

		if (handler.getPlayer().getxMove() < 0 || steadyAnimation.equals(noAttackLeft)) {

			return attackAnimationLeft.getCurrentFrame();

		} else if (handler.getPlayer().getxMove() > 0 || steadyAnimation.equals(noAttackRight)) {

			return attackAnimationRight.getCurrentFrame();

		} else if (handler.getPlayer().getyMove() < 0 || steadyAnimation.equals(noAttackUp)) {

			return attackAnimationUp.getCurrentFrame();

		} else if (handler.getPlayer().getyMove() > 0 || steadyAnimation.equals(noAttackDown)) {

			return attackAnimationDown.getCurrentFrame();

		} else {

			return null;

		}
	}

	protected void updateHitbox() {

		if (handler.getPlayer().getxMove() > 0 || steadyAnimation.equals(noAttackRight)) {
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = 20;
			getBounds().width = DEFAULT_WEAPON_WIDTH;
			getBounds().height = DEFAULT_WEAPON_HEIGHT * 2;

		} else if (handler.getPlayer().getxMove() < 0 || steadyAnimation.equals(noAttackLeft)) {			
			getBounds().x = 0;
			getBounds().y = DEFAULT_WEAPON_HEIGHT + DEFAULT_WEAPON_HEIGHT / 2;
			getBounds().width = DEFAULT_WEAPON_WIDTH;
			getBounds().height = DEFAULT_WEAPON_HEIGHT * 2;
			
		} else if (handler.getPlayer().getyMove() < 0 || steadyAnimation.equals(noAttackUp)) {			
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = 0;
			getBounds().height = DEFAULT_WEAPON_HEIGHT;
			getBounds().width = DEFAULT_WEAPON_WIDTH * 2;

		} else if (handler.getPlayer().getyMove() > 0 || steadyAnimation.equals(noAttackDown)) {			
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = DEFAULT_WEAPON_HEIGHT;
			getBounds().height = DEFAULT_WEAPON_HEIGHT;
			getBounds().width = DEFAULT_WEAPON_WIDTH * 2;

		}
	}

	protected void setWeaponDirectionEqualToPlayer() {

		if (handler.getPlayer().getSteadyAnimation().equals(Assets.player_down[1])) {

			steadyAnimation = noAttackDown;

		} else if (handler.getPlayer().getSteadyAnimation().equals(Assets.player_up[1])) {

			steadyAnimation = noAttackUp;

		} else if (handler.getPlayer().getSteadyAnimation().equals(Assets.player_left[1])) {

			steadyAnimation = noAttackLeft;

		} else if (handler.getPlayer().getSteadyAnimation().equals(Assets.player_right[1])) {

			steadyAnimation = noAttackRight;

		}

	}

	protected void disposeWeapon() {

		handler.getWorld().getEntityManager().removeEntity(this);

	}

	public int getAttackValue() {
		return attackValue;

	}

	public void setAttackValue(int attackValue) {
		this.attackValue = attackValue;

	}

	public int getStaminaUsage() {

		return staminaUsage;

	}

	@Override
	public void applyResources() {
		super.applyResources();

		steadyAnimation = noAttackDown;

	}

}
