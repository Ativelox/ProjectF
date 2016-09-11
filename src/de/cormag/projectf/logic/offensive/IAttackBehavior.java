package de.cormag.projectf.logic.offensive;

import de.cormag.projectf.entities.properties.offensive.IAttackable;
import de.cormag.projectf.utils.time.GameTime;

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
	 * @param gameTime
	 *            Object which provides various time methods and perspectives
	 *            for the current update cycle.
	 */
	public void attack(final IAttackable target, final GameTime gameTime);

	/**
	 * Whether the object is currently attacking others.
	 * 
	 * @return <tt>True</tt> if the object is currently attacking others,
	 *         <tt>false</tt> else.
	 */
	public boolean isAttacking();
}
