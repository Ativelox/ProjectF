package de.cormag.projectf.entities.properties.offensive;

/**
 * Interface for objects that can attack others.
 * 
 * @author Zabuza
 *
 */
public interface ICanAttack extends ICanOffensive {
	/**
	 * Gets the attack power of this object as DPS (damage per second).
	 * 
	 * @return The attack power of this object as DPS (damage per second)
	 */
	public float getAttackPower();

	/**
	 * Gets the stamina usage of the weapon used.
	 * 
	 * @return the stamina usage
	 */
	public int getStaminaUsage();
}
