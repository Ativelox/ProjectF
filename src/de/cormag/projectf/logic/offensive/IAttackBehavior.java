package de.cormag.projectf.logic.offensive;

import de.cormag.projectf.entities.properties.offensive.IAttackable;

/**
 * Interface for objects that define the logic how to attack others.
 * 
 * @author Zabuza
 *
 */
public interface IAttackBehavior {
	/**
	 * Attacks a given target.
	 * 
	 * @param target
	 *            The target object to attack
	 */
	public void Attack(final IAttackable target);

	/**
	 * Whether the object is currently attacking others.
	 * 
	 * @return <tt>True</tt> if the object is currently attacking others,
	 *         <tt>false</tt> else.
	 */
	public boolean isAttacking();
}
