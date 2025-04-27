package dev.samir.tips;

/**
 * Custom exception class for handling tip not found errors.
 * This class extends the Exception class and provides a constructor
 * to set the error message.
 */
public class TipNotFoundException extends Exception {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = -8603591344951501321L;
	
	/**
	 * Constructor for TipNotFoundException.
	 * @param message the error message to be displayed
	 */
	public TipNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Default constructor for TipNotFoundException.
	 * This constructor sets a default error message.
	 */
	public TipNotFoundException() {
		super("Tip not found");
	}
	
}
