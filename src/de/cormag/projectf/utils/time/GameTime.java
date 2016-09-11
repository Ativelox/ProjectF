package de.cormag.projectf.utils.time;

import java.time.Duration;
import java.time.Instant;

/**
 * Object which provides various information about the game time. For example
 * the time since last update. For creation, use factory methods like
 * {@link #createGameTimeSnapshot}.
 * 
 * @author Zabuza
 *
 */
public final class GameTime {

	/**
	 * Constant which converts seconds to nanoseconds if multiplied with.
	 */
	private static final float SECONDS_TO_NANO = 1_000_000_000.0f;

	/**
	 * Converts a time unit given in nanoseconds to seconds, with float
	 * precision.
	 * 
	 * @param nanoToConvert
	 *            The time in nanoseconds to convert
	 * @return A time unit given seconds which represents the input, with float
	 *         precision
	 */
	public static float convertNanoToSeconds(final long nanoToConvert) {
		return nanoToConvert / SECONDS_TO_NANO;
	}

	/**
	 * Creates a new game time object which from the object used in the last
	 * update. This object should be used for the second and all succeeding
	 * updates of the game.
	 * 
	 * @param lastGameTime
	 *            Game time object from the last game update cycle
	 * @return A new game time object for the current update cycle
	 */
	public static GameTime createGameTimeSnapshotFromLast(final GameTime lastGameTime) {
		GameTime time = new GameTime(lastGameTime.getGameStartTime(), Instant.now());
		return time;
	}

	/**
	 * Creates a new initial game time object which should be created and used
	 * for the first update of the game.
	 * 
	 * @return A new game time object for the first update cycle
	 */
	public static GameTime createInitialGameTimeSnapshot() {
		GameTime time = new GameTime(Instant.now(), Instant.now());
		return time;
	}

	/**
	 * Time snapshot from the start of the game, i.e. the start of the first
	 * update.
	 */
	private final Instant mGameStartTime;

	/**
	 * Time snapshot from the start of the current update.
	 */
	private final Instant mUpdateStartTime;

	/**
	 * Creates a new game time object with given time snapshots.
	 * 
	 * @param gameStartTime
	 *            Time snapshot from the start of the game, i.e. the start of
	 *            the first update
	 * @param updateStartTime
	 *            Time snapshot from the start of the current update
	 */
	private GameTime(final Instant gameStartTime, final Instant updateStartTime) {
		mGameStartTime = gameStartTime;
		mUpdateStartTime = updateStartTime;
	}

	/**
	 * Gets the duration between the start of the current update and now.
	 * 
	 * @return The duration between the start of the current update and now
	 */
	public Duration getElapsedTime() {
		return Duration.between(mUpdateStartTime, Instant.now());
	}

	/**
	 * Gets a time snapshot from the start of the game, i.e. the start of the
	 * first update.
	 * 
	 * @return A time snapshot from the start of the game, i.e. the start of the
	 *         first update
	 */
	public Instant getGameStartTime() {
		return mGameStartTime;
	}

	/**
	 * Gets the duration between the start of the game, i.e. the start of the
	 * first update, and now.
	 * 
	 * @return The duration between the start of the game, i.e. the start of the
	 *         first update, and now.
	 */
	public Duration getTotalElapsedTime() {
		return Duration.between(mUpdateStartTime, Instant.now());
	}

	/**
	 * Gets a time snapshot from the start of the current update.
	 * 
	 * @return A time snapshot from the start of the current update
	 */
	public Instant getUpdateStartTime() {
		return mUpdateStartTime;
	}
}
