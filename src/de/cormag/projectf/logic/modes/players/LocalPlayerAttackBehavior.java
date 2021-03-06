package de.cormag.projectf.logic.modes.players;

import java.util.List;

import de.cormag.projectf.entities.Entity;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.entities.properties.IHaveWeapon;
import de.cormag.projectf.entities.properties.ISpatial;
import de.cormag.projectf.entities.properties.IUpdateable;
import de.cormag.projectf.entities.properties.offensive.IOffensiveable;
import de.cormag.projectf.entities.statics.weapons.AWeapon;
import de.cormag.projectf.logic.offensive.IOffensiveBehavior;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class LocalPlayerAttackBehavior implements IUpdateable {

	protected static final String ERROR_MISSING_INTERFACE = "The given object must implement IHaveWeapon and ICanMove, see the documentation for more information.";

	final ISpatial mParent;

	final IHaveWeapon parentAsIHaveWeapon;

	final IOffensiveBehavior mOffensiveBehavior;

	final Handler mHandler;

	public LocalPlayerAttackBehavior(final ISpatial parent, final IOffensiveBehavior offensiveBehavior,
			final Handler handler) {

		mParent = parent;
		mOffensiveBehavior = offensiveBehavior;
		mHandler = handler;

		if (!(mParent instanceof ICanMove && mParent instanceof IHaveWeapon)) {
			throw new IllegalArgumentException(ERROR_MISSING_INTERFACE);
		}

		parentAsIHaveWeapon = (IHaveWeapon) mParent;

	}

	@Override
	public void update(GameTime gameTime) {

		mOffensiveBehavior.update(gameTime);

		if (mHandler.getWorld().getEntityManager() != null) {

			AWeapon currentWeapon = parentAsIHaveWeapon.getWeapon();

			if (mHandler.getWorld().getEntityManager().contains(currentWeapon)) {
				List<Entity> entityList = mHandler.getWorld().getEntityManager().getEntityList();

				for (Entity e : entityList) {
					if (e instanceof IOffensiveable) {
						if (e.getProperCollisionRectangle().intersects(currentWeapon.getProperCollisionRectangle())) {
							mOffensiveBehavior.offensiveAction((IOffensiveable) e, gameTime);
						}
					}

				}

			}
		}
	}

}
