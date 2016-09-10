package de.cormag.projectf.entities.properties.offensive;

/**
 * Interface for objects that can attack others.
 * 
 * @author Zabuza
 *
 */
public interface ICanAttack extends ICanOffensive {
	/**
	 * Gets the attack power of this object.
	 * 
	 * @return The attack power of this object
	 */
	public int getAttackPower();
}
