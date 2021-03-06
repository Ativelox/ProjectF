package de.cormag.projectf.states.hud;

import java.awt.Color;
import java.awt.Graphics;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.utils.time.GameTime;

public class MonsterHealthBar extends HUDElement {

	private Enemy enemy;
	private boolean seenPlayer, damagedOnce;

	public MonsterHealthBar(Enemy enemy) {

		this.enemy = enemy;
		seenPlayer = enemy.seenPlayer();
		damagedOnce = enemy.damagedOnce();

	}

	@Override
	public void update(final GameTime gameTime) {

		seenPlayer = enemy.seenPlayer();
		damagedOnce = enemy.damagedOnce();

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {

		if (seenPlayer || damagedOnce) {

			float healthbarWidthGray = (float) ((enemy.getWidth() / enemy.getMaxLifepoints())
					* enemy.getMaxLifepoints());
			float healthbarWidthRed = (float) ((enemy.getWidth() / enemy.getMaxLifepoints()) * enemy.getLifepoints());

			g.setColor(Color.gray);
			g.fillRect(enemy.getProperCollisionRectangle().x + ((enemy.getBounds().width / 2) - (enemy.getWidth() / 2)),
					enemy.getProperCollisionRectangle().y - 10, (int) healthbarWidthGray, 5);
			g.setColor(Color.red);
			g.fillRect(enemy.getProperCollisionRectangle().x + ((enemy.getBounds().width / 2) - (enemy.getWidth() / 2)),
					enemy.getProperCollisionRectangle().y - 10, (int) healthbarWidthRed, 5);
		}
	}

}
