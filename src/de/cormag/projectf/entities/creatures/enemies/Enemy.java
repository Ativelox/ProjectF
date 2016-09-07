package de.cormag.projectf.entities.creatures.enemies;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.entities.statics.weapons.IronSword;
import de.cormag.projectf.entities.statics.weapons.Weapon;
import de.cormag.projectf.main.Handler;

public abstract class Enemy extends Creature {

	private static final long serialVersionUID = 1L;

	protected Rectangle visionField;
	protected boolean seenPlayer;
	protected boolean damagedOnce;
	protected String name;
	protected int awardedExp;
	protected Player player;
	


	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		damaged = false;
		damagedOnce = false;
		seenPlayer = false;
		awardedExp = 0;
		name = this.getClass().getName();
		player = handler.getPlayer();

		visionField = new Rectangle((int) (x - xOffset), (int) (y - yOffset + height), width, height);

	}

	public void showDamagedNumbers(Graphics g, Enemy monster, int upperShift, int numberThickness) {

		if (damaged && seenPlayer || damaged) {
			g.setColor(new Color(255, 255, 255, 255));
			g.setFont(new Font(Font.DIALOG_INPUT, 1, 20 + numberThickness));
			g.drawString("" + IronSword.DEFAULT_IRON_SWORD_POWER,
					monster.getProperCollisionRectangle().x
							+ ((monster.getBounds().width / 2) + (monster.getWidth() / 2)),
					monster.getProperCollisionRectangle().y - upperShift);
		}
	}

	public void calculateDamageTaken(Handler handler) {
		
		if(handler.getWorld().getEntityManager() != null){

			Weapon currentWeapon = handler.getWorld().getEntityManager().getPlayer().getCurrentWeapon();
	
			if (currentWeapon != null && handler.getWorld().getEntityManager().contains(currentWeapon)) {
	
				if (this.getProperCollisionRectangle()
						.intersects(currentWeapon.getProperCollisionRectangle())) {
	
					if (!damaged) {
	
						if (this.getHealth() > 0) {
	
							this.health -= currentWeapon.getAttackValue();
							damaged = true;
	
							if (damaged) {
	
								Timer timer = new Timer();
								timer.schedule(new TimerTask() {
	
									@Override
									public void run() {
	
										damaged = false;
	
									}
								}, 400);
	
							}
	
						} else {
	
							this.health = 0;
	
						}
					}
	
				}
	
			}
		}

	}

	protected void basicAI() {

		if (seenPlayer || damagedOnce) {

			int playerX = player.getEntityCenter(xOffset, yOffset).x;
			int playerY = player.getEntityCenter(xOffset, yOffset).y;
			int monsterX = this.getEntityCenter(xOffset, yOffset).x;
			int monsterY = this.getEntityCenter(xOffset, yOffset).y;

			if (playerX > monsterX - 5) {

				this.xMove = 0;

			} else if (playerX < monsterX) {

				this.xMove = -speed;

			}

			if (playerX > monsterX) {

				this.xMove = speed;

			}

			if (playerY > monsterY - 5) {

				this.yMove = 0;

			} else if (playerY < monsterY) {

				this.yMove = -speed;

			}

			if (playerY > monsterY) {

				this.yMove = speed;

			}

			move();
		}
	}

	protected void checkIfEnemySeenPlayer() {

		if (visionField.intersects(player.getProperCollisionRectangle())) {

			seenPlayer = true;

		}

	}
	

	protected void renderVisionField(Graphics g) {

		if (!seenPlayer && !damagedOnce) {

			g.setColor(Color.CYAN);

			g.drawRoundRect(visionField.x, visionField.y, visionField.width, visionField.height, 20, 20);

		}
	}
	
	@Override
	public void tick() {
		super.tick();
		
		calculateDamageTaken(handler);

		if (damaged) {

			damagedOnce = true;

		}

		if (this.health <= 0) {

			handler.getWorld().getEntityManager().getPlayer().awardExperiencePoints(awardedExp);
			handler.getWorld().getEntityManager().removeEntity(this);

		}

		walkingAnimDown.tick();
		walkingAnimUp.tick();
		walkingAnimLeft.tick();
		walkingAnimRight.tick();

		checkIfEnemySeenPlayer();
		basicAI();

	}

	public void render(Graphics g, BufferedImage left, BufferedImage right, BufferedImage up, BufferedImage down) {

		g.drawImage(getCurrentAnimationFrame(left, right, up, down), (int) (x - xOffset), (int) (y - yOffset), width,
				height, null);

		renderVisionField(g);
		
		super.render(g);
	}

	public boolean getSeenPlayer() {

		return seenPlayer;
	}

	public boolean getDamagedOnce() {

		return damagedOnce;

	}

	public String getName() {

		return name;

	}

}
