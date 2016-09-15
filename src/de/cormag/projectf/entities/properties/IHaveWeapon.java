package de.cormag.projectf.entities.properties;

import de.cormag.projectf.entities.statics.weapons.AWeapon;

/**
 * Interface for objects that have weapons. Provides methods to get the weapon
 * the object has, or set it.
 * 
 * @author Ativelox
 *
 */

public interface IHaveWeapon {

	/**
	 * Gets the current weapon of the object.
	 * 
	 * @return the current weapon
	 */
	public AWeapon getWeapon();

	/**
	 * Sets the current weapon of the object.
	 * 
	 * @param weapon
	 *            the weapon to be set for the object
	 */
	public void setWeapon(AWeapon weapon);

}
