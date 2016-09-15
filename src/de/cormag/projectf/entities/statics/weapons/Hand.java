/**
 * 
 */
package de.cormag.projectf.entities.statics.weapons;

import java.awt.Graphics;

import de.cormag.projectf.entities.properties.IHaveWeapon;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

/**
 * The dummy weapon, which basically is perma-active and makes the hitbox of the
 * object be his weapon.
 * 
 * @author Ativelox
 *
 */
public class Hand extends AWeapon {

	private static final long serialVersionUID = 1L;

	public Hand(IHaveWeapon parent, Handler handler, float x, float y, int width, int height, int attackValue) {
		super(parent, handler, x, y, width, height, attackValue);

	}

	@Override
	public void render(final Graphics g, final GameTime gameTime) {
		super.render(g, gameTime);

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

	}

}
