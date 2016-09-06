package projectf.cormag.entities.creatures.enemies.bosses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import projectf.cormag.entities.creatures.enemies.Enemy;
import projectf.cormag.main.Handler;
import projectf.cormag.states.hud.BossHealthBar;

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
