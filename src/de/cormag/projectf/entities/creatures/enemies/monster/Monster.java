package de.cormag.projectf.entities.creatures.enemies.monster;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.hud.MonsterHealthBar;

public abstract class Monster extends Enemy implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient MonsterHealthBar monsterHealthBar;

	public Monster(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		this.handler = handler;
		
		monsterHealthBar = new MonsterHealthBar(this);

	}

	public void tick() {
		super.tick();

		monsterHealthBar.tick();

	}

	public void render(Graphics g, BufferedImage left, BufferedImage right, BufferedImage up, BufferedImage down) {
		super.render(g, left, right, up, down);

		monsterHealthBar.render(g);
		showDamagedNumbers(g, this, 0, 0);

	}

}
