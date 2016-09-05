package projectf.cormag.states.hud;

import java.awt.Color;
import java.awt.Graphics;

import projectf.cormag.entities.creatures.enemies.Enemy;

public class MonsterHealthBar extends HUDElement {

	private Enemy enemy;
	private boolean seenPlayer, damagedOnce;

	public MonsterHealthBar(Enemy enemy) {

		this.enemy = enemy;
		seenPlayer = enemy.getSeenPlayer();
		damagedOnce = enemy.getDamagedOnce();

	}

	@Override
	public void tick() {

		seenPlayer = enemy.getSeenPlayer();
		damagedOnce = enemy.getDamagedOnce();

	}

	@Override
	public void render(Graphics g) {

		if (seenPlayer || damagedOnce) {

			float healthbarWidthGray = (float) ((enemy.getWidth() / enemy.getMaxHealth()) * enemy.getMaxHealth());
			float healthbarWidthRed = (float) ((enemy.getWidth() / enemy.getMaxHealth()) * enemy.getHealth());

			g.setColor(Color.gray);
			g.fillRect(
					enemy.getProperCollisionRectangle().x
							+ ((enemy.getBounds().width / 2) - (enemy.getWidth() / 2)),
					enemy.getProperCollisionRectangle().y - 10, (int) healthbarWidthGray, 5);
			g.setColor(Color.red);
			g.fillRect(
					enemy.getProperCollisionRectangle().x
							+ ((enemy.getBounds().width / 2) - (enemy.getWidth() / 2)),
					enemy.getProperCollisionRectangle().y - 10, (int) healthbarWidthRed, 5);
		}
	}

}
