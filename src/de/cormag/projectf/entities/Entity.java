package de.cormag.projectf.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Iterator;

import de.cormag.projectf.entities.creatures.humans.talkable.TalkableHuman;
import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.properties.ISpatial;
import de.cormag.projectf.entities.properties.IUpdateable;
import de.cormag.projectf.entities.statics.weapons.AWeapon;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public abstract class Entity implements Serializable, IRenderable, IUpdateable, ISpatial {

	public static final int DEFAULT_COLLISION_PADDDING = 7;

	private static final long serialVersionUID = 1L;
	private Rectangle bounds;
	private Entity e;
	protected transient Handler handler;
	protected boolean hasMoved;

	protected int width, height;

	protected float x, y;

	protected float xOffset, yOffset;

	/**
	 * Holds the x-coordinate relative to the camera, i.e. the world position,
	 * of the last tick.
	 */
	private float mOldRelativeX;
	/**
	 * Holds the y-coordinate relative to the camera, i.e. the world position,
	 * of the last tick.
	 */
	private float mOldRelativeY;
	/**
	 * Holds the current x-coordinate relative to the camera, i.e. the world
	 * position.
	 */
	private float mRelativeX;
	/**
	 * Holds the current y-coordinate relative to the camera, i.e. the world
	 * position.
	 */
	private float mRelativeY;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		xOffset = 0;
		yOffset = 0;

		setBounds(new Rectangle(0, 0, width, height));

		setOldRelativeX(x);
		setOldRelativeY(y);
		setRelativeX(x);
		setRelativeY(y);
	}

	public void applyResources() {
	}

	public boolean checkEntityCollisions(double xOffset, double yOffset) {

		if (handler.getWorld().getEntityManager() != null) {

			Iterator<Entity> entities = handler.getWorld().getEntityManager().getEntities();

			while (entities.hasNext()) {
				e = entities.next();

				if (e.equals(this))
					continue;
				if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))
						&& !(e instanceof AWeapon))
					return true;
			}
			return false;

		} else {

			return false;

		}
	}

	public Iterator<Entity> getEntityIterator() {
		return handler.getWorld().getEntityManager().getEntities();

	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Entity getCollidingEntity() {

		return e;

	}

	public Rectangle getCollisionBounds(double xOffset, double yOffset) {
		return new Rectangle((int) (getRelativeX() + getBounds().x + xOffset),
				(int) (getRelativeY() + getBounds().y + yOffset), getBounds().width, getBounds().height);
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

		return new Point((int) (getX() + width / 2), (int) (getY() + height / 2));

	}

	public int getHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}

	public Rectangle getNearbyRectangle() {

		Rectangle collisionRect = getCollisionBounds(xOffset, yOffset);
		Rectangle properCollisionRect = new Rectangle((int) ((collisionRect.x - xOffset * 2) - 6),
				(int) ((collisionRect.y - yOffset * 2) - 6), collisionRect.width + 12, collisionRect.height + 12);
		return properCollisionRect;
	}

	public Rectangle getProperCollisionRectangle() {

		Rectangle collisionRect = getCollisionBounds(xOffset, yOffset);
		Rectangle properCollisionRect = new Rectangle((int) (collisionRect.x - xOffset * 2),
				(int) (collisionRect.y - yOffset * 2), collisionRect.width, collisionRect.height);
		return properCollisionRect;
	}

	public int getWidth() {
		return width;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(final Graphics g, final GameTime gameTime) {

		renderHitBox(g, gameTime);

	}

	public TalkableHuman returnNearStandingTalkableHuman() {

		if (handler.getWorld().getEntityManager() != null) {

			Iterator<Entity> entities = handler.getWorld().getEntityManager().getEntities();

			while (entities.hasNext()) {
				Entity ex = entities.next();

				if (ex instanceof TalkableHuman
						&& ex.getProperCollisionRectangle().intersects(this.getNearbyRectangle())) {

					return (TalkableHuman) ex;

				}
			}
		}
		return null;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;

	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void stopMusic() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {

		setOldRelativeX(getRelativeX());
		setOldRelativeY(getRelativeY());

		xOffset = handler.getGameCamera().getxOffset();
		yOffset = handler.getGameCamera().getyOffset();

		// // Translate relative movement to absolute
		setX(getRelativeX() - xOffset);
		setY(getRelativeY() - yOffset);

		hasMoved = getOldRelativeX() != getRelativeX() || getOldRelativeY() != getRelativeY();
	}

	protected void renderHitBox(final Graphics g, final GameTime gameTime) {

		g.setColor(Color.orange);
		g.drawRect((int) getX() + bounds.x, (int) getY() + bounds.y, bounds.width, bounds.height);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getOldRelativeX()
	 */
	@Override
	public float getOldRelativeX() {
		return mOldRelativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getOldRelativeY()
	 */
	@Override
	public float getOldRelativeY() {
		return mOldRelativeY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getRelativeX()
	 */
	@Override
	public float getRelativeX() {
		return mRelativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#getRelativeY()
	 */
	@Override
	public float getRelativeY() {
		return mRelativeY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cormag.projectf.entities.properties.ISpatial#setOldRelativeX(float)
	 */
	@Override
	public void setOldRelativeX(float oldRelativeX) {
		this.mOldRelativeX = oldRelativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cormag.projectf.entities.properties.ISpatial#setOldRelativeY(float)
	 */
	@Override
	public void setOldRelativeY(float oldRelativeY) {
		this.mOldRelativeY = oldRelativeY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#setRelativeX(float)
	 */
	@Override
	public void setRelativeX(float relativeX) {
		this.mRelativeX = relativeX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.ISpatial#setRelativeY(float)
	 */
	@Override
	public void setRelativeY(float relativeY) {
		this.mRelativeY = relativeY;
	}
}