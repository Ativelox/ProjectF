package de.cormag.projectf.states.hud;

import java.awt.Color;
import java.awt.Graphics;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class BossHealthBar extends HUDElement {

	private Enemy enemy;
	private float health, maxHealth;
	private String name;
	private transient Handler handler;
	private boolean damaged;

	public BossHealthBar(Enemy enemy, Handler handler) {

		this.enemy = enemy;
		this.handler = handler;

		health = enemy.getLifepoints();
		maxHealth = enemy.getMaxLifepoints();
		name = enemy.getName();
		damaged = enemy.getDamaged();

	}

	@Override
	public void update(final GameTime gameTime) {

		health = enemy.getLifepoints();
		maxHealth = enemy.getMaxLifepoints();
		name = enemy.getName();
		damaged = enemy.getDamaged();

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {

		g.setColor(Color.WHITE);
		g.setFont(Assets.OPTIMUS_PRINCEPS.deriveFont(25f));
		g.drawString(name, 100, 625);

		g.setColor(new Color(200, 200, 200, 100));
		g.fillRect(100, 640, (int) ((800 / maxHealth) * maxHealth), 20);

		g.setColor(new Color(150, 0, 0));
		g.fillRect(100, 640, (int) ((800 / maxHealth) * health), 20);

		g.setColor(new Color(250, 0, 0));
		g.drawLine(98, 638, 901, 638);
		g.drawLine(98, 639, 901, 639);

		g.drawLine(98, 638, 98, 661);
		g.drawLine(99, 638, 99, 661);

		g.drawLine(98, 660, 901, 660);
		g.drawLine(98, 661, 901, 661);

		g.drawLine(900, 638, 900, 661);
		g.drawLine(901, 638, 901, 661);

		for (int i = 1; i < health; i++) {

			if (((800 / maxHealth) * (i * 10)) < 800) {

				if (handler.getWorld().getEntityManager()
						.contains(handler.getWorld().getEntityManager().getPlayer().getCurrentWeapon())) {

					if (damaged) {
						g.setColor(new Color(255, 255, 255, 255));
						g.setFont(Assets.OPTIMUS_PRINCEPS.deriveFont(30f));
						g.drawString(""
								+ handler.getWorld().getEntityManager().getPlayer().getCurrentWeapon().getAttackValue(),
								860, 620);
					}

				}

				g.setColor(Color.black);
				g.drawLine((int) ((800 / maxHealth) * (i * 10)) + 100, 640, (int) (((800 / maxHealth) * (i * 10)) + 100), 659);

			}
		}

	}

}
