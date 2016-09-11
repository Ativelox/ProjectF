package de.cormag.projectf.logic.modes.players;

import de.cormag.projectf.entities.creatures.humans.controlable.ControlableHuman;
import de.cormag.projectf.logic.modes.AModeManager;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.modes.UnsupportedModeException;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Manages player modes. Forwards events to the currently active mode.
 * 
 * @author Ativelox
 *
 */

public class PlayerModeManager extends AModeManager{
	
	/**
	 * the player which is being controlled by this object
	 */
	@SuppressWarnings("unused")
	private final ControlableHuman mParent;
	
	/**
	 * The control object for the mode {@link EPlayerMode#LOCAL_PLAYER_MODE LOCAL_PLAYER_MODE}.
	 */
	private final IModeControl mLocalPlayerControl;
	
	/**
	 * the currently active mode
	 */
	private EPlayerMode mCurrentMode;
	
	/**
	 * Creates a new player mode manager which manages player modes and forwards events to the currently active mode.
	 * @param parent	the player which is being controlled by this object
	 * @param localPlayerControl	the control object for the mode {@link EPlayerMode#LOCAL_PLAYER_MODE LOCAL_PLAYER_MODE}
	 * @param initialMode	the initial mode to be set for this object
	 */
	public PlayerModeManager(final ControlableHuman parent, final IModeControl localPlayerControl, final EPlayerMode initialMode) {
		super(parent);
		
		mParent = parent;
		mLocalPlayerControl = localPlayerControl;
		
		mCurrentMode = initialMode;
		getCurrentModeControl().activate();
	}
	
	/**
	 * Gets the current active mode.
	 * 
	 * @return The current active mode
	 */
	public EPlayerMode getCurrentMode() {
		return mCurrentMode;
	}

	@Override
	public void update(GameTime gameTime) {
		getCurrentModeControl().update(gameTime);
		
	}

	@Override
	protected IModeControl getCurrentModeControl() {
		if(mCurrentMode == EPlayerMode.LOCAL_PLAYER_MODE){
			return mLocalPlayerControl;
		}else{
			throw new UnsupportedModeException(ERROR_UNKNOWN_MODE + mCurrentMode);
		}
	}

}
