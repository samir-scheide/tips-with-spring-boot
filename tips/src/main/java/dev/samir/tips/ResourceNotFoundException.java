package dev.samir.tips;

/**
 * Custom exception class for handling resource not found errors.
 * This class extends the Exception class and provides a constructor
 * to set the error message.
 */
public class ResourceNotFoundException extends Exception {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = -8603591344951501321L;
	
	/**
	 * Constructor for ResourceNotFoundException.
	 * @param message the error message to be displayed
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
