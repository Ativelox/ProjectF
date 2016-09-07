package de.cormag.projectf.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Iterator;

import de.cormag.projectf.entities.creatures.humans.talkable.TalkableHuman;
import de.cormag.projectf.entities.statics.nocollision.NoCollision;
import de.cormag.projectf.main.Handler;

public abstract class Entity implements Serializable, Comparable<Entity> {

	private static final long serialVersionUID = 1L;

	protected transient Handler handler;
	protected float x, y;
	protected int width, height;
	private Rectangle bounds;
	public static final int DEFAULT_COLLISION_PADDDING = 7;
	
	private Entity e;

	protected float xOffset, yOffset;

	protected boolean hasMoved;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		xOffset = 0;
		yOffset = 0;

		setBounds(new Rectangle(0, 0, width, height));
	}

	public void tick() {
		
		xOffset = handler.getGameCamera().getxOffset();
		yOffset = handler.getGameCamera().getyOffset();
		
		hasMoved = false;
	}

	public void render(Graphics g){
		
		renderHitBox(g);
		
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		
		if(handler.getWorld().getEntityManager() != null){
	
			Iterator<Entity> entities = handler.getWorld().getEntityManager().getEntities();
	
			while (entities.hasNext()) {
				e = entities.next();
				
				if (e.equals(this))
					continue;
				if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
					return true;
			}
			return false;
			
		}else{
			
			return false;
			
		}
	}
	
	public TalkableHuman returnNearStandingTalkableHuman(){
		
		if(handler.getWorld().getEntityManager() != null){
			
			Iterator<Entity> entities = handler.getWorld().getEntityManager().getEntities(); 
	
			while(entities.hasNext()){
				Entity ex = entities.next();
				
				if(ex instanceof TalkableHuman && ex.getProperCollisionRectangle().intersects(this.getNearbyRectangle())){
					
					return (TalkableHuman)ex;
					
				}
			}
		}
		return null;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + getBounds().x + xOffset), (int) (y + getBounds().y + yOffset),
				getBounds().width, getBounds().height);
	}

	public Point getCollisionCenter(float xOffset, float yOffset) {

		Rectangle collisionRect = getCollisionBounds(xOffset, yOffset);
		Rectangle properCollisionRect = new Rectangle((int) (collisionRect.x - xOffset * 2),
				(int) (collisionRect.y - yOffset * 2), collisionRect.width, collisionRect.height);
		Point properCollisionCenter = new Point((int) properCollisionRect.getCenterX(),
				(int) properCollisionRect.getCenterY());

		return properCollisionCenter;

	}

	public Point getEntityCenter(float xOffset, float yOffset) {

		return new Point((int) (x - xOffset + width / 2), (int) (y - yOffset + height / 2));

	}

	public Rectangle getProperCollisionRectangle() {

		Rectangle collisionRect = getCollisionBounds(xOffset, yOffset);
		Rectangle properCollisionRect = new Rectangle((int) (collisionRect.x - xOffset * 2),
				(int) (collisionRect.y - yOffset * 2), collisionRect.width, collisionRect.height);
		return properCollisionRect;
	}
	
	public Rectangle getNearbyRectangle() {

		Rectangle collisionRect = getCollisionBounds(xOffset, yOffset);
		Rectangle properCollisionRect = new Rectangle((int) ((collisionRect.x - xOffset * 2) - 6),
				(int) ((collisionRect.y - yOffset * 2) - 6), collisionRect.width + 12, collisionRect.height + 12);
		return properCollisionRect;
	}

	protected void renderHitBox(Graphics g) {

		g.setColor(Color.orange);
		g.drawRect((int) (x + bounds.x - xOffset), (int) (y + bounds.y - yOffset), bounds.width, bounds.height);

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;

	}

	public void applyResources() {
	}

	public void stopMusic() {
	}
	
	public Entity getCollidingEntity(){
		
		return e;
		
	}

	@Override
	public int compareTo(Entity o) {
	
		if(this instanceof NoCollision){
			return -1;
			
		}else if(o instanceof NoCollision){
			
			return 1;
			
		}
		
		if (this.getY() + this.getHeight() < o.getY() + o.getHeight()) {
			return -1;
		} else if (this.getY() + this.getHeight() == o.getY() + o.getHeight()) {
			return 0;
		} else if(this.getY() + this.getHeight() > o.getY() + o.getHeight()){
			return 1;
		}else{
			
			return -1;
		}
	}
	
}