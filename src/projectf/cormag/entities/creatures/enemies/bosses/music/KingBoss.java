package projectf.cormag.entities.creatures.enemies.bosses.music;

import java.awt.Graphics;
import java.io.Serializable;

import projectf.cormag.entities.creatures.humans.Player;
import projectf.cormag.gfx.Animation;
import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;
import projectf.cormag.states.hud.BossHealthBar;

public class KingBoss extends BossWithMusic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final int KING_WIDTH = 70, KING_HEIGHT = 75;
	
	private BossHealthBar bossHealthBar;

	public KingBoss(Handler handler, float x, float y) {
		super(handler, x, y, KING_WIDTH, KING_HEIGHT, "boss/Let Me Hear.pfsf");
		
		this.handler = handler;
		this.health = 10;
		maxHealth = this.health;
		damaged = false;
		attackValue = 1;

		speed = Player.DEFAULT_SPEED / 2;

		awardedExp = 1000;

		sprinting = false;
		
		bossHealthBar = new BossHealthBar(this, handler);

		applyResources();
		
	}
	
	@Override
	public void tick(){
		super.tick();
		
	}

	@Override
	public void render(Graphics g) {
		super.render(g, Assets.king_left[1], Assets.king_right[1], Assets.king_up[1], Assets.king_down[1]);
	}
	
	@Override
	public void applyResources(){
		super.applyResources();
		
		walkingAnimDown = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_down);
		walkingAnimUp = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_up);
		walkingAnimLeft = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_left);
		walkingAnimRight = new Animation(Player.WALKING_ANIMATION_SPEED, Assets.king_right);

		steadyAnimation = Assets.king_down[1];
	}

	@Override
	public BossHealthBar getBossHealthBar() {
		
		return bossHealthBar;
	}
}
