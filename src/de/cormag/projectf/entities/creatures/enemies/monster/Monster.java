package de.cormag.projectf.entities.creatures.enemies.monster;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.MonsterHealthBar;

public abstract class Monster extends Enemy implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient MonsterHealthBar monsterHealthBar;
	private int tickcounts;
	
	private String lastMove;

	public Monster(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		tickcounts = 0;
		
		this.handler = handler;
		
		visionField = new Rectangle((int) (x - xOffset - width / 2), (int) (y - yOffset + height), width * 2, height * 2);
		
		monsterHealthBar = new MonsterHealthBar(this);

	}
	
	@Override
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

			
			
		}else if(tickcounts == 0 && !(seenPlayer || damagedOnce)){
			
			if(Math.round(Math.random()) == 0){
				yMove = 0;
				this.xMove = ((int) Math.round((Math.random() * 2) - 1)) * speed;
				
			}else{
				xMove = 0;
				this.yMove = ((int) Math.round((Math.random() * 2) - 1)) * speed;
				
			}
		}
		
		move();
		tickcounts++;
	}
	
	protected void changeVisionField(){	

		
		if(this.xMove > 0){
			
			visionField.setBounds((int) (x - xOffset + getBounds().x + getBounds().width),(int) (y - yOffset - height / 2), height * 2, width * 2);
			
			lastMove = "right";
			
			
		}else if(this.xMove < 0){
			
			visionField.setBounds((int) (x - xOffset - width * 2 + getBounds().x),(int) (y - yOffset - height / 2), height * 2, width * 2);
			
			lastMove = "left";
			
			
		}else if(this.yMove > 0){
			
			visionField.setBounds((int) (x - xOffset - width / 2), (int) (y - yOffset + getBounds().height + getBounds().y), width * 2, height * 2);
			
			lastMove = "down";
			
			
		}else if(this.yMove < 0){
			
			visionField.setBounds((int) (x - xOffset - width / 2),(int) (y - yOffset - height * 2 + getBounds().y), height * 2, width * 2);
			
			lastMove = "up";
			
		}else{
			
			if(lastMove != null){
			
				switch(lastMove){
				
				case "right" : visionField.setBounds((int) (x - xOffset + getBounds().x + getBounds().width),(int) (y - yOffset - height / 2), height * 2, width * 2);
				break;
				
				case "left" : visionField.setBounds((int) (x - xOffset - width * 2 + getBounds().x),(int) (y - yOffset - height / 2), height * 2, width * 2);
				break;
				
				case "down" : visionField.setBounds((int) (x - xOffset - width / 2), (int) (y - yOffset + getBounds().height + getBounds().y), width * 2, height * 2);
				break;
				
				case "up" : visionField.setBounds((int) (x - xOffset - width / 2),(int) (y - yOffset - height * 2 + getBounds().y), height * 2, width * 2);
				break;
				
				default : visionField.setBounds((int) (x - xOffset - width / 2), (int) (y - yOffset + getBounds().height + getBounds().y), width * 2, height * 2);
				break;
				}
				
			}
		}
	}
	
	public void tick() {
		super.tick();
		
		if(tickcounts >= 60){
			
			tickcounts = 0;
			
		}
			
		visionField.setBounds((int) (x - xOffset - width / 2), (int) (y - yOffset + getBounds().height + getBounds().y), width * 2, height * 2);

		changeVisionField();
		

		monsterHealthBar.tick();
	}

	public void render(Graphics g, BufferedImage left, BufferedImage right, BufferedImage up, BufferedImage down) {
		super.render(g, left, right, up, down);

		monsterHealthBar.render(g);
		showDamagedNumbers(g, this, 0, 0);

	}

}
