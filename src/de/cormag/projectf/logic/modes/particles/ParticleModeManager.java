package de.cormag.projectf.logic.modes.particles;

import de.cormag.projectf.entities.particles.Particle;
import de.cormag.projectf.logic.modes.AModeManager;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.modes.UnsupportedModeException;

/**
 * Manages a particles modes. Transitions between modes when necessary and
 * forwards events to the currently active mode.
 * 
 * @author Zabuza
 *
 */
public final class ParticleModeManager extends AModeManager {

	/**
	 * Error message when trying to pass a mode which is not supported
	 */
	private static final String ERROR_UNKNOWN_MODE = "Particle mode is not supported by this object: ";
	/**
	 * The current active mode.
	 */
	private EParticleMode mCurrentMode;
	/**
	 * The control object for the mode {@link EParticleMode#PASSIVE_WOBBLE
	 * PASSIVE_WOBBLE}.
	 */
	private final IModeControl mPassiveWobbleControl;

	/**
	 * Creates a new particle mode manager which belongs to a given particle.
	 * 
	 * @param parent
	 *            The particle this object manages mode for.
	 */
	public ParticleModeManager(final Particle parent, final IModeControl passiveWobbleControl,
			final EParticleMode startMode) {
		super(parent);

		mPassiveWobbleControl = passiveWobbleControl;

		mCurrentMode = startMode;
		getCurrentModeControl().activate();
	}

	/**
	 * Gets the current active mode.
	 * 
	 * @return The current active mode
	 */
	public EParticleMode getCurrentMode() {
		return mCurrentMode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update() {
		getCurrentModeControl().update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.modes.AModeManager#getCurrentModeControl()
	 */
	@Override
	protected IModeControl getCurrentModeControl() {
		if (mCurrentMode == EParticleMode.PASSIVE_WOBBLE) {
			return mPassiveWobbleControl;
		} else {
			throw new UnsupportedModeException(ERROR_UNKNOWN_MODE + mCurrentMode);
		}
	}

}
