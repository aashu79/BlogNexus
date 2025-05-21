package utils;

/**
 * Utility class representing the result of a validation operation.
 */
public class ValidationResult {
	private boolean success;
	private String message;

	/**
	 * Constructs a ValidationResult with the specified success flag and message.
	 *
	 * @param success Whether the validation was successful
	 * @param message The message describing the validation result
	 */
	public ValidationResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	/**
	 * Returns whether the validation was successful.
	 *
	 * @return true if the validation was successful, false otherwise
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Returns the message describing the validation result.
	 *
	 * @return The validation result message
	 */
	public String getMessage() {
		return message;
	}
}