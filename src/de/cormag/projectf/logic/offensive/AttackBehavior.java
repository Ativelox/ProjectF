package de.cormag.projectf.logic.offensive;

import de.cormag.projectf.entities.properties.ILively;
import de.cormag.projectf.entities.properties.offensive.IAttackable;
import de.cormag.projectf.entities.properties.offensive.ICanAttack;

/**
 * Behavior which implements a basic attack logic for objects.
 * 
 * @author Zabuza
 *
 */
public final class AttackBehavior implements IAttackBehavior {

	/**
	 * Whether the object is currently attacking others.
	 */
	private boolean mIsAttacking;
	/**
	 * The object to attack with to which this behavior belongs to.
	 */
	private final ICanAttack mParent;

	/**
	 * Creates a new basic attack behavior that can attack other objects.
	 * 
	 * @param parent
	 *            Object to attack with
	 */
	public AttackBehavior(final ICanAttack parent) {
		mParent = parent;
		mIsAttacking = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.offensive.IAttackBehavior#Attack(de.cormag.
	 * projectf.entities.properties.offensive.IAttackable)
	 */
	@Override
	public void Attack(final IAttackable target) {
		// TODO Some logic to determine if units are on different sides or not

		mIsAttacking = true;

		if (target instanceof ILively) {
			ILively targetAsLively = (ILively) target;

			int lifepointLoss = mParent.getAttackPower();
			targetAsLively.changeLifepoints(-lifepointLoss);

			// TODO Maybe play some sounds or do different stuff
		}

		mIsAttacking = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.offensive.IAttackBehavior#isAttacking()
	 */
	@Override
	public boolean isAttacking() {
		return mIsAttacking;
	}

}