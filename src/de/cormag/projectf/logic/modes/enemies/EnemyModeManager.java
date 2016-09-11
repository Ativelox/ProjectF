package de.cormag.projectf.logic.modes.enemies;

import de.cormag.projectf.entities.creatures.enemies.Enemy;
import de.cormag.projectf.logic.modes.AModeManager;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.modes.UnsupportedModeException;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Manages a enemy modes. Transitions between modes when necessary and forwards
 * events to the currently active mode.
 * 
 * @author Zabuza
 *
 */
public final class EnemyModeManager extends AModeManager {

	/**
	 * The control object for the mode {@link EEnemyMode#AGGRESSIVE AGGRESSIVE}.
	 */
	private final IModeControl mAggressiveControl;
	/**
	 * The current active mode.
	 */
	private EEnemyMode mCurrentMode;
	/**
	 * The control object for the mode {@link EEnemyMode#DEFENSIVE DEFENSIVE}.
	 */
	private final IModeControl mDefensiveControl;
	
	@SuppressWarnings("unused")
	private final Enemy mParent;

	/**
	 * Creates a new enemy mode manager which belongs to a given enemy.
	 * 
	 * @param parent
	 *            The enemy this object manages modes for.
	 * @param aggressiveControl
	 *            The control object which should be used in mode
	 *            {@link EEnemyMode#AGGRESSIVE AGGRESSIVE}
	 * @param defensiveControl
	 *            The control object which should be used in mode
	 *            {@link EEnemyMode#DEFENSIVE DEFENSIVE}
	 * @param initialMode
	 *            The initial mode for the object
	 */
	public EnemyModeManager(final Enemy parent, final IModeControl aggressiveControl,
			final IModeControl defensiveControl, final EEnemyMode initialMode) {
		super(parent);
		
		mParent = parent;
		mAggressiveControl = aggressiveControl;
		mDefensiveControl = defensiveControl;

		mCurrentMode = initialMode;
		getCurrentModeControl().activate();
	}

	/**
	 * Gets the current active mode.
	 * 
	 * @return The current active mode
	 */
	public EEnemyMode getCurrentMode() {
		return mCurrentMode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {
		getCurrentModeControl().update(gameTime);

//		if(getCurrentMode() != EEnemyMode.AGGRESSIVE && (mParent.seenPlayer() || mParent.damagedOnce())){
//			getCurrentModeControl.terminate();
//		    mCurrentMode = EEnemyMode.AGGRESSIVE;
//			getCurrentModeControl.activate();
//		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.modes.AModeManager#getCurrentModeControl()
	 */
	@Override
	protected IModeControl getCurrentModeControl() {
		if (mCurrentMode == EEnemyMode.AGGRESSIVE) {
			return mAggressiveControl;
		} else if (mCurrentMode == EEnemyMode.DEFENSIVE) {
			return mDefensiveControl;
		} else {
			throw new UnsupportedModeException(ERROR_UNKNOWN_MODE + mCurrentMode);
		}
	}

}
