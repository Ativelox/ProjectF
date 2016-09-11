package de.cormag.projectf.utils.time;

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
	 * Creates a new game time object which from the object used in the last
	 * update. This object should be used for the second and all succeeding
	 * updates of the game.
	 * 
	 * @param lastGameTime
	 *            Game time object from the last game update cycle
	 * @param lastUpdateEndTime
	 *            Time snapshot from the end of the last update cycle.
	 * @return A new game time object for the current update cycle
	 */
	public static GameTime createGameTimeSnapshotFromLast(final GameTime lastGameTime, final Time lastUpdateEndTime) {
		GameTime time = new GameTime(lastGameTime.getGameStartTime(), lastUpdateEndTime);
		return time;
	}

	/**
	 * Creates a new initial game time object which should be created and used
	 * for the first update of the game.
	 * 
	 * @return A new game time object for the first update cycle
	 */
	public static GameTime createInitialGameTimeSnapshot() {
		GameTime time = new GameTime(Time.now(), Time.now());
		return time;
	}

	/**
	 * Time snapshot from the start of the game, i.e. the start of the first
	 * update.
	 */
	private final Time mGameStartTime;

	/**
	 * Time snapshot from the end of the last update cycle.
	 */
	private final Time mLastUpdateEndTime;

	/**
	 * Creates a new game time object with given time snapshots.
	 * 
	 * @param gameStartTime
	 *            Time snapshot from the start of the game, i.e. the start of
	 *            the first update
	 * @param lastUpdateEndTime
	 *            Time snapshot from the end of the last update cycle.
	 */
	private GameTime(final Time gameStartTime, final Time lastUpdateEndTime) {
		mGameStartTime = gameStartTime;
		mLastUpdateEndTime = lastUpdateEndTime;
	}

	/**
	 * Gets the duration between the end of the last update and now.
	 * 
	 * @return The duration between the end of the last update and now
	 */
	public Time getElapsedTime() {
		return Time.between(mLastUpdateEndTime, Time.now());
	}

	/**
	 * Gets a time snapshot from the start of the game, i.e. the start of the
	 * first update.
	 * 
	 * @return A time snapshot from the start of the game, i.e. the start of the
	 *         first update
	 */
	public Time getGameStartTime() {
		return mGameStartTime;
	}

	/**
	 * Gets a time snapshot from the end of the last update cycle.
	 * 
	 * @return A time snapshot from the end of the last update cycle.
	 */
	public Time getLastUpdateEndTime() {
		return mLastUpdateEndTime;
	}

	/**
	 * Gets the duration between the start of the game, i.e. the start of the
	 * first update, and now.
	 * 
	 * @return The duration between the start of the game, i.e. the start of the
	 *         first update, and now.
	 */
	public Time getTotalElapsedTime() {
		return Time.between(mGameStartTime, Time.now());
	}
}
