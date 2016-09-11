package de.cormag.projectf.entities.creatures.enemies.monster;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.MonsterHealthBar;
import de.cormag.projectf.utils.time.GameTime;

public abstract class Monster extends Enemy implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient MonsterHealthBar monsterHealthBar;
	private int tickcounts;

	private String lastMove;
	
	private boolean isBugged;
	private int padding;

	public Monster(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		tickcounts = 0;

		this.handler = handler;
		
		isBugged = false;
		if(!isBugged){
			padding = 20;
		}else{
			padding = 0;	
		}
		//TODO: temporary fix, visionfield clipping into the hitbox of its own entity, resulting in the entity hitting itself

		visionField = new Rectangle((int) (getX() - width / 2), (int) (getY() + height + padding), width * 2,
				height * 2);

		monsterHealthBar = new MonsterHealthBar(this);

	}

	protected void changeVisionField() {

		if (this.xMove > 0) {

			visionField.setBounds((int) (getX() + getBounds().x + getBounds().width + padding),
					(int) (getY() - height / 2), height * 2, width * 2);

			lastMove = "right";

		} else if (this.xMove < 0) {

			visionField.setBounds((int) (getX() - width * 2 + getBounds().x - padding), (int) (getY() - height / 2),
					height * 2, width * 2);

			lastMove = "left";

		} else if (this.yMove > 0) {

			visionField.setBounds((int) (getX() - width / 2),
					(int) (getY() + getBounds().height + getBounds().y + padding), width * 2, height * 2);

			lastMove = "down";

		} else if (this.yMove < 0) {

			visionField.setBounds((int) (getX() - width / 2), (int) (getY() - height * 2 + getBounds().y - padding),
					height * 2, width * 2);

			lastMove = "up";

		} else {

			if (lastMove != null) {

				switch (lastMove) {

				case "right":
					visionField.setBounds((int) (getX() + getBounds().x + getBounds().width + padding),
							(int) (getY() - height / 2), height * 2, width * 2);
					break;

				case "left":
					visionField.setBounds((int) (getX() - width * 2 + getBounds().x - padding), (int) (getY() - height / 2),
							height * 2, width * 2);
					break;

				case "down":
					visionField.setBounds((int) (getX() - width / 2),
							(int) (getY() + getBounds().height + getBounds().y + padding), width * 2, height * 2);
					break;

				case "up":
					visionField.setBounds((int) (getX() - width / 2), (int) (getY() - height * 2 + getBounds().y - padding),
							height * 2, width * 2);
					break;

				default:
					visionField.setBounds((int) (getX() - width / 2),
							(int) (getY() + getBounds().height + getBounds().y + padding), width * 2, height * 2);
					break;
				}

			}
		}
	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		if (tickcounts >= 60) {

			tickcounts = 0;

		}

		visionField = new Rectangle((int) (getX() - width / 2), (int) (getY() + height + padding), width * 2, height * 2);
		
		changeVisionField();

		monsterHealthBar.update(gameTime);
	}

	@Override
	public void render(Graphics g, final GameTime gameTime, BufferedImage imageToDraw) {
		super.render(g, gameTime, imageToDraw);
		
		monsterHealthBar.render(g, gameTime);
		showDamagedNumbers(g, this, 0, 0);

	}

}
