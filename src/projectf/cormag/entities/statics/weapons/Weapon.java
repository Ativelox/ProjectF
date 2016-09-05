package projectf.cormag.entities.statics.weapons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import projectf.cormag.entities.EntityManager;
import projectf.cormag.entities.creatures.Creature;
import projectf.cormag.entities.statics.StaticEntity;
import projectf.cormag.gfx.Animation;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public abstract class Weapon extends StaticEntity {

	private static final long serialVersionUID = 1L;

	protected static final int DEFAULT_WEAPON_WIDTH = 32;
	protected static final int DEFAULT_WEAPON_HEIGHT = 32;

	protected transient BufferedImage steadyAnimation;

	protected boolean attack;
	protected boolean dispose;

	protected int attackValue;
	protected int staminaUsage;

	protected transient Animation attackAnimationRight, attackAnimationLeft, attackAnimationUp, attackAnimationDown;

	protected transient BufferedImage noAttackRight, noAttackLeft, noAttackUp, noAttackDown;

	protected int rightArmX, rightArmY, leftArmX, leftArmY, upperArmX, upperArmY, lowerArmX, lowerArmY;

	public Weapon(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		xOffset = handler.getGameCamera().getxOffset();
		yOffset = handler.getGameCamera().getyOffset();

		attack = false;
		attackValue = 1;
		dispose = false;

		applyResources();

	}

	protected void updatePlayerArms(EntityManager manager) {
		
		if(manager != null){

			rightArmX = (int) manager.getPlayer().getX() + ((Creature.DEFAULT_CREATURE_WIDTH / 2) + 8);
			rightArmY = (int) manager.getPlayer().getY() - ((Creature.DEFAULT_CREATURE_HEIGHT / 4));
	
			leftArmX = (int) manager.getPlayer().getX() - ((Creature.DEFAULT_CREATURE_WIDTH / 2) + 8);
			leftArmY = (int) manager.getPlayer().getY() - (Creature.DEFAULT_CREATURE_HEIGHT / 4);
	
			upperArmX = (int) manager.getPlayer().getX() - ((Creature.DEFAULT_CREATURE_WIDTH / 2));
			upperArmY = (int) manager.getPlayer().getY() - ((Creature.DEFAULT_CREATURE_HEIGHT / 2) + 5);
	
			lowerArmX = (int) manager.getPlayer().getX() - ((Creature.DEFAULT_CREATURE_WIDTH / 2));
			lowerArmY = (int) manager.getPlayer().getY() + ((Creature.DEFAULT_CREATURE_HEIGHT) - 15);
			
		}
	}
		

	protected void updateWeaponPosition(float playerArmX, float playerArmY) {

		this.x = playerArmX;
		this.y = playerArmY;

	}

	protected void updateCorrespondingPlayeArms(EntityManager manager) {
		
		if(manager != null){
		
			if (steadyAnimation == null || manager.getPlayer() == null) {
				return;
	
			}
	
			if (manager.getPlayer().getxMove() > 0 || steadyAnimation.equals(noAttackRight)) {
	
				updateWeaponPosition(rightArmX, rightArmY);
	
			} else if (manager.getPlayer().getxMove() < 0 || steadyAnimation.equals(noAttackLeft)) {
	
				updateWeaponPosition(leftArmX, leftArmY);
	
			} else if (manager.getPlayer().getyMove() < 0 || steadyAnimation.equals(noAttackUp)) {
	
				updateWeaponPosition(upperArmX, upperArmY);
	
			} else if (manager.getPlayer().getyMove() > 0 || steadyAnimation.equals(noAttackDown)) {
	
				updateWeaponPosition(lowerArmX, lowerArmY);
	
			}
		}

	}

	protected BufferedImage getCurrentAnimationFrame(EntityManager manager) {

		if (steadyAnimation == null || manager == null || manager.getPlayer() == null) {
			return attackAnimationDown.getCurrentFrame();

		}

		if (manager.getPlayer().getxMove() < 0 || steadyAnimation.equals(noAttackLeft)) {

			return attackAnimationLeft.getCurrentFrame();

		} else if (manager.getPlayer().getxMove() > 0 || steadyAnimation.equals(noAttackRight)) {

			return attackAnimationRight.getCurrentFrame();

		} else if (manager.getPlayer().getyMove() < 0 || steadyAnimation.equals(noAttackUp)) {

			return attackAnimationUp.getCurrentFrame();

		} else if (manager.getPlayer().getyMove() > 0 || steadyAnimation.equals(noAttackDown)) {

			return attackAnimationDown.getCurrentFrame();

		} else {

			return null;

		}
	}

	protected void renderWeapon(Graphics g, EntityManager manager, float xOffset, float yOffset) {

		if (steadyAnimation == null || manager.getPlayer() == null) {
			return;

		}

		if (manager.getPlayer().getxMove() > 0 || steadyAnimation.equals(noAttackRight)) {
			// right
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = DEFAULT_WEAPON_HEIGHT + DEFAULT_WEAPON_HEIGHT / 2;

			g.drawImage(getCurrentAnimationFrame(manager), (int) (x - xOffset), (int) (y - yOffset), null);

			steadyAnimation = noAttackRight;

		} else if (manager.getPlayer().getxMove() < 0 || steadyAnimation.equals(noAttackLeft)) {
			// left
			getBounds().x = 0;
			getBounds().y = DEFAULT_WEAPON_HEIGHT + DEFAULT_WEAPON_HEIGHT / 2;

			g.drawImage(getCurrentAnimationFrame(manager), (int) (x - xOffset), (int) (y - yOffset), null);

			steadyAnimation = noAttackLeft;

		} else if (manager.getPlayer().getyMove() < 0 || steadyAnimation.equals(noAttackUp)) {
			// up
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = 0;
			getBounds().height = DEFAULT_WEAPON_HEIGHT;
			getBounds().width = DEFAULT_WEAPON_WIDTH * 2;

			g.drawImage(getCurrentAnimationFrame(manager), (int) (x - xOffset), (int) (y - yOffset), null);

			steadyAnimation = noAttackUp;

		} else if (manager.getPlayer().getyMove() > 0 || steadyAnimation.equals(noAttackDown)) {
			// down
			getBounds().x = DEFAULT_WEAPON_WIDTH;
			getBounds().y = DEFAULT_WEAPON_HEIGHT;
			getBounds().height = DEFAULT_WEAPON_HEIGHT;
			getBounds().width = DEFAULT_WEAPON_WIDTH * 2;

			g.drawImage(getCurrentAnimationFrame(manager), (int) (x - xOffset), (int) (y - yOffset), null);

			steadyAnimation = noAttackDown;

		}
	}

	protected void checkPlayerDirection(Handler handler) {

		if (handler.getWorld().getEntityManager().getPlayer().getSteadyAnimation().equals(Assets.player_down[1])) {

			steadyAnimation = noAttackDown;

		} else if (handler.getWorld().getEntityManager().getPlayer().getSteadyAnimation().equals(Assets.player_up[1])) {

			steadyAnimation = noAttackUp;

		} else if (handler.getWorld().getEntityManager().getPlayer().getSteadyAnimation()
				.equals(Assets.player_left[1])) {

			steadyAnimation = noAttackLeft;

		} else if (handler.getWorld().getEntityManager().getPlayer().getSteadyAnimation()
				.equals(Assets.player_right[1])) {

			steadyAnimation = noAttackRight;

		}

	}

	protected void disposeWeapon(Handler handler) {

		if (dispose) {

			handler.getWorld().getEntityManager().removeEntity(this);

			dispose = false;

		}
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
