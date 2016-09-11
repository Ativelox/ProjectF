package de.cormag.projectf.logic.offensive;

import de.cormag.projectf.entities.properties.offensive.IAttackable;
import de.cormag.projectf.entities.properties.offensive.ICanAttack;
import de.cormag.projectf.entities.properties.offensive.ICanOffensive;
import de.cormag.projectf.entities.properties.offensive.IOffensiveable;
import de.cormag.projectf.logic.modes.UnsupportedModeException;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Behavior which resolves offensive actions by choosing from offensive modes
 * dependent on the objects properties.
 * 
 * @author Zabuza
 *
 */
public final class OffensiveBehavior implements IOffensiveBehavior {

	/**
	 * All offensive modes of this behavior.
	 * 
	 * @author Zabuza
	 *
	 */
	public enum OffensiveMode {
		/**
		 * Offensive mode where objects attack others.
		 */
		ATTACK;
	}

	/**
	 * Error message when trying to pass a parent object with unknown mode.
	 */
	private final static String ERROR_UNKNOWN_MODE = "Parent object is not supported by this object.";

	/**
	 * The behavior object to use for attacking targets.
	 */
	private final IAttackBehavior mAttackBehavior;
	/**
	 * The current offensive mode of this behavior.
	 */
	private final OffensiveMode mMode;

	/**
	 * Creates a new basic offensive behavior that can cast offensive actions
	 * against others based on the objects properties.
	 * 
	 * @param parent
	 *            Object to cast offensive actions with
	 */
	public OffensiveBehavior(final ICanOffensive parent) {
		if (parent instanceof ICanAttack) {
			ICanAttack parentAsCanAttack = (ICanAttack) parent;
			mMode = OffensiveMode.ATTACK;
			mAttackBehavior = new AttackBehavior(parentAsCanAttack);
		} else {
			throw new UnsupportedModeException(ERROR_UNKNOWN_MODE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.offensive.IOffensiveBehavior#isOffensive()
	 */
	@Override
	public boolean isOffensive() {
		if (mMode == OffensiveMode.ATTACK) {
			return mAttackBehavior.isAttacking();
		} else {
			throw new UnsupportedModeException(ERROR_UNKNOWN_MODE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cormag.projectf.logic.offensive.IOffensiveBehavior#offensiveAction(de.
	 * cormag.projectf.entities.properties.offensive.IOffensiveable)
	 */
	@Override
	public void offensiveAction(final IOffensiveable target, final GameTime gameTime) {
		if (mMode == OffensiveMode.ATTACK) {
			if (target instanceof IAttackable) {
				IAttackable targetAsAttackable = (IAttackable) target;
				mAttackBehavior.attack(targetAsAttackable, gameTime);
			}
		} else {
			throw new UnsupportedModeException(ERROR_UNKNOWN_MODE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {

	}

}
