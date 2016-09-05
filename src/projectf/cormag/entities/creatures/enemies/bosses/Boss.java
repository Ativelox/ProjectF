package projectf.cormag.entities.creatures.enemies.bosses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import projectf.cormag.entities.creatures.enemies.Enemy;
import projectf.cormag.main.Handler;
import projectf.cormag.states.hud.BossHealthBar;

public abstract class Boss extends Enemy {

	private static final long serialVersionUID = 1L;
	private BossHealthBar bossHealthBar;
	private boolean hasHealthBar;

	public Boss(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		hasHealthBar = false;

	}

	public void render(Graphics g, BufferedImage left, BufferedImage right, BufferedImage up, BufferedImage down) {
		super.render(g, left, right, up, down);

	}

	public void tick() {
		super.tick();

		if ((seenPlayer || damagedOnce) && !hasHealthBar) {

			bossHealthBar = new BossHealthBar(this, handler);
			handler.getGame().getStateManager().getGameState().getHUDState().addHUDElement(bossHealthBar);
			hasHealthBar = true;

		}

		if (health <= 0) {

			handler.getGame().getStateManager().getGameState().getHUDState().removeHUDElement(bossHealthBar);

		}

	}

}
