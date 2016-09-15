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
	public float getLifepoints();

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
	public void changeLifepoints(final float amount);

	/**
	 * Sets the lifepoints of the object to the given amount.
	 * 
	 * @param amount
	 *            Amount to set lifepoints to
	 */
	void setLifepoints(final float amount);

	/**
	 * Gets the maximum lifepoints this unit can possess. Lifepoints cannot
	 * exceed the maximum lifepoints.
	 * 
	 * @return The maximum lifepoints of the object
	 */
	public float getMaxLifepoints();
}
