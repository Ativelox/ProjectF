package de.cormag.projectf.logic.modes.particles;

import java.awt.geom.Point2D;

import de.cormag.projectf.entities.properties.ISpatial;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.movement.IMoveBehavior;

/**
 * Control for a passive mode where the parent object floats and wobbles
 * passively around.
 * 
 * @author Zabuza
 *
 */
public final class PassiveWobbleControl implements IModeControl {

	/**
	 * The maximal amplitude in x-direction of the movement pattern in pixel.
	 */
	private final static float AMPLITUDE_X = 40;
	/**
	 * The maximal amplitude in y-direction of the movement pattern in pixel.
	 */
	private final static float AMPLITUDE_Y = 15;
	/**
	 * The duration of a half movement pattern period in milliseconds.
	 */
	private final static int PERIOD_MILLIS = 5000;
	/**
	 * Whether the mode currently is active or not. Controlled by
	 * {@link #activate()} and {@link #terminate()}
	 */
	private boolean mIsActive;
	/**
	 * Whether the unit currently is in the first half of the movement pattern
	 * period.
	 */
	private boolean mIsInFirstPeriodHalf;
	/**
	 * The place in period of the last update. This is a number between 0 and
	 * {@link HALF_PERIOD_MILLIS}.
	 */
	private float mLastPlaceInPeriod;
	/**
	 * Move order receiving behavior object.
	 */
	private final IMoveBehavior mMoveReceiver;
	/**
	 * The entity which is controlled by this object.
	 */
	private final ISpatial mParent;
	/**
	 * Position of unit at the activation of the control.
	 */
	private Point2D mStartingPosition;

	/**
	 * Creates a new passive wobble control object which controls the given
	 * parent unit.
	 * 
	 * @param parent
	 *            The entity which is controlled by this object
	 * @param moveReceiver
	 *            Move order receiving behavior object
	 */
	public PassiveWobbleControl(final ISpatial parent, final IMoveBehavior moveReceiver) {
		mParent = parent;
		mMoveReceiver = moveReceiver;
		mIsActive = false;
		mIsInFirstPeriodHalf = true;
		mLastPlaceInPeriod = 0;
		mStartingPosition = new Point2D.Float(mParent.getRelativeX(), mParent.getRelativeY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.modes.IModeControl#activate()
	 */
	@Override
	public void activate() {
		mIsActive = true;
		mIsInFirstPeriodHalf = true;
		mStartingPosition = new Point2D.Float(mParent.getRelativeX(), mParent.getRelativeY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.logic.modes.IModeControl#terminate()
	 */
	@Override
	public void terminate() {
		mIsActive = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update() {
		// Pass update call to movement receiver
		mMoveReceiver.update();

		// Do not do update the control if not activated
		if (!mIsActive) {
			return;
		}

		// Sine wave like move pattern
		long millis = System.currentTimeMillis();
		float placeInPeriod = millis % PERIOD_MILLIS;

		// This indicates the period is over and direction has to be changed
		boolean isPeriodOver = mLastPlaceInPeriod > placeInPeriod;
		if (isPeriodOver) {
			mIsInFirstPeriodHalf = !mIsInFirstPeriodHalf;
		}

		float xOffset = (placeInPeriod / PERIOD_MILLIS) * AMPLITUDE_X;
		float yOffset = (float) (Math.sin((placeInPeriod / PERIOD_MILLIS) * 2 * Math.PI) * AMPLITUDE_Y);

		// The direction of the movement in x-direction needs to be to the left
		// in the second half of the period
		if (!mIsInFirstPeriodHalf) {
			xOffset *= -1;
		}

		// Compute the position
		float posX = (float) mStartingPosition.getX() + xOffset;
		float posY = (float) mStartingPosition.getY() + yOffset;
		if (!mIsInFirstPeriodHalf) {
			// If direction changes, the starting point of computation needs to
			// be the total amplitude to the right, not the starting position
			// itself
			posX += AMPLITUDE_X;
		}

		// Apply the movement to the unit
		mMoveReceiver.moveTo(posX, posY);

		mLastPlaceInPeriod = placeInPeriod;
	}

}
