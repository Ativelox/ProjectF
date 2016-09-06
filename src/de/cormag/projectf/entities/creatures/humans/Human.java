package de.cormag.projectf.entities.creatures.humans;

import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.main.Handler;

public abstract class Human extends Creature {

	private static final long serialVersionUID = 1L;

	public Human(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

	}

	protected void calculatePlayerGettingDamage(float xOffset, float yOffset, int enemyWidth) {

		if (checkEntityCollisions(0f, 10f) || checkEntityCollisions(10f, 0f) || checkEntityCollisions(0f, -10f)
				|| checkEntityCollisions(-10f, 0f)) {
			
			if(getCollidingEntity() instanceof Creature){
			
				if (!damaged) {
	
					if (this.health > 0) {
	
						this.health -= ((Creature) getCollidingEntity()).getAttackValue();
	
						damaged = true;
	
						if (damaged) {
	
							Timer timer = new Timer();
							timer.schedule(new TimerTask() {
	
								@Override
								public void run() {
	
									damaged = false;
	
								}
							}, 1000);
						}
	
					} else {
	
						this.health = 0;
	
					}
				}
	
			}
			
		}

	}
}
