package de.cormag.projectf.entities.properties;

/**
 * Interface for objects that have lifepoints. Provides methods to increase and
 * decrease lifepoints.
 * 
 * @author Zabuza
 *
 */
public interface ILively {

	/**
	 * Gets the current lifepoints of the object.
	 * 
	 * @return The current lifepoints of the object
	 */
	public int getLifepoints();

	/**
	 * Whether the object is alive.
	 * 
	 * @return <tt>True</tt> if the object is alive, <tt>false</tt> else.
	 */
	public boolean isAlive();

	/**
	 * Changes the lifepoints of the object by the given amount. Decreases if
	 * amount is negative and increases if positive.
	 * 
	 * @param amount
	 *            Amount to change lifepoints. Decreases if negative and
	 *            increases if positive.
	 */
	void changeLifepoints(final int amount);

}
