package de.cormag.projectf.logic.modes;

/**
 * Exception which is thrown when an entity tries to enter a mode which it does
 * not support.
 * 
 * @author Zabuza
 *
 */
public final class UnsupportedModeException extends RuntimeException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new unsupported mode exception with a given message.
	 * 
	 * @param message
	 *            Message providing additional information
	 */
	public UnsupportedModeException(final String message) {
		super(message);
	}

}
