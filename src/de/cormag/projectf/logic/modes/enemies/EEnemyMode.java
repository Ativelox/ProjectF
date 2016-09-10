package de.cormag.projectf.logic.modes.enemies;

/**
 * Mutually-exclusive enemy modes.
 * 
 * @author Zabuza
 *
 */
public enum EEnemyMode {
	/**
	 * Enemy acts offensive, actively searches for other entities and targets
	 * them.
	 */
	AGGRESSIVE,
	/**
	 * Enemy acts defensively, it will only attack other entities if they get
	 * very near.
	 */
	DEFENSIVE
}
