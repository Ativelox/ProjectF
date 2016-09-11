package de.cormag.projectf.entities.properties;

/**
 * Interface for entities that have movement speed. Provides methods to get and set the speed of the object.
 * 
 * @author Ativelox
 */


public interface IHaveSpeed {
	
	/**
	 * Gets the current movement speed of the object.
	 * 
	 * @return The movement speed the object has
	 */
	public float getMovementSpeed();
	
	/**
	 * Sets the movement speed of the object to the given value.
	 * @param amount
	 * 			the value the movement speed will be set to
	 * 
	 */
	public void setMovementSpeed(float amount);
	
	/**
	 * Gets the current running speed of the object.
	 * 
	 * @return The running speed the object has
	 */
	public float getRunningSpeed();
	
	/**
	 * Sets the running speed of the object to the given value
	 * 
	 * @param amount
	 * 			the value the running speed will be set to
	 */			
	public void setRunningSpeed(float amount);
	
	/**
	 * Gets the horizontal movement of the object. The greater the number the faster the object moves. 
	 * Positive values resemble movement to the right, negative values resemble movement to the left.
	 * 
	 * @return the horizontal movement value.
	 */
	public float getXMove();
	
	/**
	 * Sets the horizontal movement of the object. The greater the number the faster the object moves. 
	 * Positive values resemble movement to the right, negative values resemble movement to the left.
	 * 
	 * @param amount the horizontal movement value to be set
	 */
	public void setXMove(float amount);
	
	/**
	 * Gets the vertical movement of the object. The greater the number the faster the object moves. 
	 * Positive values resemble movement downwards, negative values resemble movement upwards.
	 * 
	 * @return the horizontal movement value.
	 */
	public float getYMove();
	
	/**
	 * Sets the vertical movement of the object. The greater the number the faster the object moves. 
	 * Positive values resemble movement downwards, negative values resemble movement upwards.
	 * 
	 * @param amount the vertical movement value to be set
	 */
	public void setYMove(float amount);
		
	/**
	 * Gets whether the object is sprinting or not
	 * @return <tt>true</tt> if the object is sprinting <tt>false</tt> if the object isn't sprinting
	 */
	public boolean isSprinting();
	

}
