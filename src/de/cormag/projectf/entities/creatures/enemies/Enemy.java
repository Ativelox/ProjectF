package de.cormag.projectf.entities.creatures.enemies;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.entities.properties.ILively;
import de.cormag.projectf.entities.properties.offensive.IAttackable;
import de.cormag.projectf.entities.properties.offensive.ICanAttack;
import de.cormag.projectf.entities.statics.weapons.IronSword;
import de.cormag.projectf.logic.modes.AModeManager;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.modes.enemies.AggressiveControl;
import de.cormag.projectf.logic.modes.enemies.DefensiveControl;
import de.cormag.projectf.logic.modes.enemies.EEnemyMode;
import de.cormag.projectf.logic.modes.enemies.EnemyModeManager;
import de.cormag.projectf.logic.movement.IMoveBehavior;
import de.cormag.projectf.logic.movement.MoveBehavior;
import de.cormag.projectf.logic.offensive.IOffensiveBehavior;
import de.cormag.projectf.logic.offensive.OffensiveBehavior;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public abstract class Enemy extends Creature implements ILively, IAttackable, ICanAttack{

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_HEALTH = 10;
	
	protected Rectangle visionField;
	protected boolean seenPlayer;
	protected boolean damagedOnce;
	protected String name;
	protected int awardedExp;
	protected Player player;
	protected float health;
	protected float maxHealth;
	protected boolean damaged;
	protected int attackValue;
	
	private AModeManager mModeManager;

	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		health = DEFAULT_HEALTH;
		maxHealth = DEFAULT_HEALTH;
		damaged = false;
		damagedOnce = false;
		seenPlayer = false;
		awardedExp = 0;
		name = this.getClass().getName();
		player = handler.getPlayer();
		
		IMoveBehavior moveBehavior = new MoveBehavior(this);
		IOffensiveBehavior offensiveBehavior = new OffensiveBehavior(this);
		IModeControl aggresiveControl = new AggressiveControl(this, moveBehavior, offensiveBehavior, handler);
		IModeControl defensiveControl = new DefensiveControl(this, moveBehavior, offensiveBehavior, handler);
		mModeManager = new EnemyModeManager(this, aggresiveControl, defensiveControl, EEnemyMode.AGGRESSIVE);

		visionField = new Rectangle((int) (getX()), (int) (getY() + height), width, height);

	}

	public void showDamagedNumbers(Graphics g, Enemy monster, int upperShift, int numberThickness) {

		if (damaged && seenPlayer || damaged) {
			g.setColor(new Color(255, 255, 255, 255));
			g.setFont(new Font(Font.DIALOG_INPUT, 1, 20 + numberThickness));
			g.drawString("" + IronSword.DEFAULT_IRON_SWORD_POWER,
					monster.getProperCollisionRectangle().x
							+ ((monster.getBounds().width / 2) + (monster.getWidth() / 2)),
					monster.getProperCollisionRectangle().y - upperShift);
		}
	}

	protected void checkIfEnemySeenPlayer() {

		if (visionField.intersects(player.getProperCollisionRectangle())) {

			seenPlayer = true;

		}

	}

	protected void renderVisionField(Graphics g) {

		if (!seenPlayer && !damagedOnce) {

			g.setColor(Color.CYAN);

			g.drawRoundRect(visionField.x, visionField.y, visionField.width, visionField.height, 20, 20);

		}
	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);
		
		mModeManager.update(gameTime);

		if (damaged) {

			damagedOnce = true;

		}

		if (this.health <= 0) {

			handler.getWorld().getEntityManager().getPlayer().awardExperiencePoints(awardedExp);
			handler.getWorld().getEntityManager().removeEntity(this);

		}

		walkingAnimDown.tick();
		walkingAnimUp.tick();
		walkingAnimLeft.tick();
		walkingAnimRight.tick();

		checkIfEnemySeenPlayer();

	}

	public void render(Graphics g, final GameTime gameTime, BufferedImage imageToDraw) {
		renderVisionField(g);
		super.render(g, gameTime, imageToDraw);
		
	}
	
	public Rectangle getVisionField(){
		return this.visionField;
		
	}
	
	@Override
	public float getAttackPower(){
		return attackValue;
		
	}
	
	@Override
	public float getLifepoints(){
		return health;
		
	}

	@Override
	public boolean isAlive(){
		if(health > 0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public void changeLifepoints(final float amount){
		if(health + amount > maxHealth){
			health = maxHealth;
			
		}else{
			health += amount;
			
		}
	}
	
	@Override
	public void setLifepoints(float amount) {
		if(amount > maxHealth){
			health = maxHealth;
		}else{
			health = amount;
		}
		
	}

	@Override
	public float getMaxLifepoints() {
		return maxHealth;
	}

	public boolean seenPlayer() {

		return seenPlayer;
	}
	
	public void setSeenPlayer(boolean seen){
		seenPlayer = seen;
		
	}

	public boolean damagedOnce() {

		return damagedOnce;

	}

	public String getName() {

		return name;

	}
	public boolean getDamaged() {

		return damaged;
	}

	public void setDamaged(boolean bool) {

		this.damaged = bool;

	}


}
