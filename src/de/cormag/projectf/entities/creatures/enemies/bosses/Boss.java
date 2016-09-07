package de.cormag.projectf.entities.creatures.enemies.bosses;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.BossHealthBar;

public abstract class Boss extends Enemy {

	private static final long serialVersionUID = 1L;
	private boolean reDrawHealthbarCooldown = false;

	public Boss(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

	}

	public void render(Graphics g, BufferedImage left, BufferedImage right, BufferedImage up, BufferedImage down) {
		super.render(g, left, right, up, down);

	}

	public void tick() {
		super.tick();
		
		visionField = new Rectangle((int) (x - xOffset - width / 2), (int) (y - yOffset + height), width * 2, height * 2);

		if ((seenPlayer || damagedOnce) && 
				!handler.getGame().getStateManager().getGameState().getHUDState().containsHUDElement(getBossHealthBar())
				&& !reDrawHealthbarCooldown){

			
			addHealthBar(getBossHealthBar());

		}

		if (health <= 0) {

			removeHealthBar(getBossHealthBar());

		}

	}

	
	public void addHealthBar(BossHealthBar bossHealthBar){
		
		handler.getGame().getStateManager().getGameState().getHUDState().addHUDElement(bossHealthBar);
		
	}
	
	public void removeHealthBar(BossHealthBar bossHealthBar){
		
		reDrawHealthbarCooldown = true;
		
		handler.getGame().getStateManager().getGameState().getHUDState().removeHUDElement(bossHealthBar);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				reDrawHealthbarCooldown = false;
				
			}
		
			
		}, 1000);
		
	}
	
	public abstract BossHealthBar getBossHealthBar();

}
