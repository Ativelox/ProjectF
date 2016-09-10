package de.cormag.projectf.logic.offensive;

import de.cormag.projectf.entities.properties.IUpdateable;
import de.cormag.projectf.entities.properties.offensive.IOffensiveable;

/**
 * Interface for objects that define the logic how to do offensive actions.
 * 
 * @author Zabuza
 *
 */
public interface IOffensiveBehavior extends IUpdateable {
	/**
	 * Whether the object is currently casting offensive actions.
	 * 
	 * @return <tt>True</tt> if the object is currently casting offensive
	 *         actions, <tt>false</tt> else.
	 */
	public boolean isOffensive();

	/**
	 * Casts an offensive action against a given target. The object can decide
	 * the type of offensive action itself.
	 * 
	 * @param target
	 *            Target to be offensive to
	 */
	public void offensiveAction(final IOffensiveable target);
}
