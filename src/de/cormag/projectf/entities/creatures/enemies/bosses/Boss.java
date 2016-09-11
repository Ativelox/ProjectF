package de.cormag.projectf.entities.creatures.enemies.bosses;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.BossHealthBar;
import de.cormag.projectf.utils.time.GameTime;

public abstract class Boss extends Enemy {

	private static final long serialVersionUID = 1L;
	private boolean reDrawHealthbarCooldown = false;

	public Boss(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		visionField = new Rectangle((int) (getX() - width / 2 + 20), (int) (getY() + height), width * 2,
				height * 2);

		if ((seenPlayer || damagedOnce) && !handler.getGame().getStateManager().getGameState().getHUDState()
				.containsHUDElement(getBossHealthBar()) && !reDrawHealthbarCooldown) {

			addHealthBar(getBossHealthBar());

		}

		if (health <= 0) {

			removeHealthBar(getBossHealthBar());

		}

	}

	public void addHealthBar(BossHealthBar bossHealthBar) {

		handler.getGame().getStateManager().getGameState().getHUDState().addHUDElement(bossHealthBar);

	}

	public void removeHealthBar(BossHealthBar bossHealthBar) {

		reDrawHealthbarCooldown = true;

		handler.getGame().getStateManager().getGameState().getHUDState().removeHUDElement(bossHealthBar);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				reDrawHealthbarCooldown = false;

			}

		}, 1000);

	}

	public abstract BossHealthBar getBossHealthBar();

}
