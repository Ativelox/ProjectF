package de.cormag.projectf.entities.statics.weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.properties.IHaveWeapon;
import de.cormag.projectf.entities.properties.offensive.ICanAttack;
import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Abstract class which represents all kinds of weapons.
 * 
 * @author Ativelox
 *
 */
public abstract class AWeapon extends StaticEntity implements ICanAttack {

	private static final long serialVersionUID = 1L;

	protected static final String ERROR_MISSING_PARENT = "the given object must be a child of Entity";

	private final IHaveWeapon mParent;

	private float mAttackValue;

	public AWeapon(IHaveWeapon parent, Handler handler, float x, float y, int width, int height, float attackValue) {
		super(handler, x, y, width, height);

		mParent = parent;

		mAttackValue = attackValue;

		if (!(mParent instanceof Entity)) {
			throw new IllegalArgumentException(ERROR_MISSING_PARENT);
		}

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		renderHitBox(g, gameTime);
	}

	@Override
	public void update(GameTime gameTime) {
		super.update(gameTime);

		updateHitbox();

	}

	@Override
	protected void renderHitBox(final Graphics g, final GameTime gameTime) {
		g.setColor(Color.green);
		g.drawRect((int) getX() + getBounds().x, (int) getY() + getBounds().y, getBounds().width, getBounds().height);

	}

	/**
	 * Updates the hitbox of the corresponding weapon, set to the hitbox of the
	 * object which has the weapon as default.
	 */
	public void updateHitbox() {
		Rectangle bounds = getBounds();
		Rectangle parentBounds = ((Entity) mParent).getBounds();

		setRelativeX(((Entity) mParent).getRelativeX());
		setRelativeY(((Entity) mParent).getRelativeY());

		bounds.x = parentBounds.x - 10;
		bounds.y = parentBounds.y - 10;
		bounds.width = parentBounds.width + 20;
		bounds.height = parentBounds.height + 20;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.offensive.ICanAttack#
	 * getStaminaUsage()
	 */
	@Override
	public int getStaminaUsage() {
		return 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.offensive.ICanAttack#
	 * getAttackPower()
	 */
	@Override
	public float getAttackPower() {
		return mAttackValue;
	}

}
