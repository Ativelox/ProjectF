package de.cormag.projectf.utils.time;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * Object which represents a time value, this can be a snapshot or a duration
 * for example. The object is immutable and thread-safe.
 * 
 * @author Zabuza
 *
 */
public final class Time {
	/**
	 * Error message which is used if a given temporal type is not supported.
	 */
	private static final String ERROR_UNSUPPORTED_TEMPORAL_TYPE = "The given temporal unit is not supported by this operation: ";
	/**
	 * Constant which converts hours to nanoseconds if multiplied with.
	 */
	private static final double HOURS_TO_NANO = 3_600_000_000_000.0;
	/**
	 * Constant which converts milliseconds to nanoseconds if multiplied with.
	 */
	private static final double MILLIS_TO_NANO = 1_000_000.0;
	/**
	 * Constant which converts milliseconds to nanoseconds if multiplied with.
	 */
	private static final double MINUTES_TO_NANO = 60_000_000_000.0;

	/**
	 * Constant which converts seconds to nanoseconds if multiplied with.
	 */
	private static final double SECONDS_TO_NANO = 1_000_000_000.0;

	/**
	 * Creates a time object which represents the time between two given time
	 * objects.
	 * 
	 * @param startInclusive
	 *            The first time object
	 * @param endExclusive
	 *            The second time object
	 * @return A time object which represents the time between two given time
	 *         objects.
	 */
	public static Time between(final Time startInclusive, final Time endExclusive) {
		return new Time((long) Math.abs(endExclusive.get(ChronoUnit.NANOS) - startInclusive.get(ChronoUnit.NANOS)));
	}

	/**
	 * Creates a time object which represents a snapshot of the current time.
	 * 
	 * @return A time object which represents a snapshot of the current time.
	 */
	public static Time now() {
		return new Time(System.nanoTime());
	}

	/**
	 * Time in nanoseconds represented by this object.
	 */
	private final long mNanoSeconds;

	/**
	 * Creates a new time object which represents the given value.
	 * 
	 * @param nanoSeconds
	 *            Time in nanoseconds represented by this object
	 */
	public Time(final long nanoSeconds) {
		mNanoSeconds = nanoSeconds;
	}

	/**
	 * Gets the time represented by this object converted to the given unit with
	 * double precision based on nanoseconds.
	 * 
	 * @param chronoUnit
	 *            The unit to get, supported types are {@link ChronoUnit#NANOS},
	 *            {@link ChronoUnit#MILLIS}, {@link ChronoUnit#SECONDS},
	 *            {@link ChronoUnit#MINUTES} and {@link ChronoUnit#HOURS}
	 * @return The time represented by this object converted to the given unit
	 *         with double precision based on nanoseconds
	 * @throws UnsupportedTemporalTypeException
	 *             If the given time unit is not supported by this operation
	 */
	public double get(final TemporalUnit chronoUnit) throws UnsupportedTemporalTypeException {
		if (chronoUnit == ChronoUnit.NANOS) {
			return mNanoSeconds;
		} else if (chronoUnit == ChronoUnit.MILLIS) {
			return mNanoSeconds / MILLIS_TO_NANO;
		} else if (chronoUnit == ChronoUnit.SECONDS) {
			return mNanoSeconds / SECONDS_TO_NANO;
		} else if (chronoUnit == ChronoUnit.MINUTES) {
			return mNanoSeconds / MINUTES_TO_NANO;
		} else if (chronoUnit == ChronoUnit.HOURS) {
			return mNanoSeconds / HOURS_TO_NANO;
		} else {
			throw new UnsupportedTemporalTypeException(ERROR_UNSUPPORTED_TEMPORAL_TYPE + chronoUnit);
		}
	}
}
