package utils;

import java.util.regex.Pattern;
import jakarta.servlet.http.Part;

/**
 * Utility class for validation operations.
 */
public class ValidationUtils {

	/**
	 * Checks if a string is null, empty, or contains only whitespace.
	 *
	 * @param value The string to check.
	 * @return true if the string is null, empty, or contains only whitespace; false
	 *         otherwise.
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Checks if a string contains only letters.
	 *
	 * @param value The string to check.
	 * @return true if the string contains only letters; false otherwise.
	 */
	public static boolean isAlphabetic(String value) {
		return value != null && value.matches("^[a-zA-Z]+$");
	}

	/**
	 * Validates if an email address is in a proper format.
	 *
	 * @param email The email address to validate.
	 * @return true if the email is valid; false otherwise.
	 */
	public static boolean isValidEmail(String email) {
		if (isNullOrEmpty(email)) {
			return false;
		}
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return Pattern.matches(emailRegex, email);
	}

	/**
	 * Validates a phone number format.
	 *
	 * @param phoneNumber The phone number to validate.
	 * @return true if the phone number is valid; false otherwise.
	 */
	public static boolean isValidPhoneNumberFormat(String phoneNumber) {
		if (isNullOrEmpty(phoneNumber)) {
			return true;
		}
		String phoneRegex = "^\\+?[0-9\\s\\-\\(\\)]+$";
		return phoneNumber.matches(phoneRegex);
	}

	/**
	 * Validates password complexity. Password must have at least one lowercase
	 * letter, one uppercase letter, one digit, one special character, and be at
	 * least 8 characters long.
	 *
	 * @param password The password to validate.
	 * @return true if the password meets the complexity requirements; false
	 *         otherwise.
	 */
	public static boolean isValidPasswordComplexity(String password) {
		if (isNullOrEmpty(password)) {
			return false;
		}
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password.matches(passwordRegex);
	}

	/**
	 * Checks if two passwords match.
	 *
	 * @param password        The first password.
	 * @param confirmPassword The second password.
	 * @return true if the passwords match; false otherwise.
	 */
	public static boolean doPasswordsMatch(String password, String confirmPassword) {
		return password != null && password.equals(confirmPassword);
	}

	/**
	 * Checks if a country is selected (not null or empty).
	 *
	 * @param countryValue The selected country value.
	 * @return true if a country is selected; false otherwise.
	 */
	public static boolean isCountrySelected(String countryValue) {
		return !isNullOrEmpty(countryValue);
	}

	/**
	 * Validates if a file is an image based on its extension.
	 *
	 * @param imagePart The file to validate.
	 * @return true if the file is an image or null/empty; false otherwise.
	 */
	public static boolean isValidImageFile(Part imagePart) {
		if (imagePart == null || imagePart.getSize() == 0 || isNullOrEmpty(imagePart.getSubmittedFileName())) {
			return true;
		}
		String fileName = imagePart.getSubmittedFileName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif") || fileName.endsWith(".webp") || fileName.endsWith(".bmp");
	}

	/**
	 * Validates the size of a file.
	 *
	 * @param imagePart      The file to validate.
	 * @param maxSizeInBytes The maximum allowed size in bytes.
	 * @return true if the file size is within the limit; false otherwise.
	 */
	public static boolean isValidFileSize(Part imagePart, long maxSizeInBytes) {
		if (imagePart == null || imagePart.getSize() == 0) {
			return true;
		}
		return imagePart.getSize() <= maxSizeInBytes;
	}

	/**
	 * Verifies form details, returning ValidationResult with success and a short
	 * message. This method is intended for cases where the profile picture is
	 * provided as a String.
	 *
	 * @param firstName       The first name.
	 * @param lastName        The last name.
	 * @param email           The email address.
	 * @param phone           The phone number.
	 * @param country         The selected country.
	 * @param password        The password.
	 * @param confirmPassword The confirm password.
	 * @param profilePicture  The profile picture filename.
	 * @return A ValidationResult object with the validation result and message.
	 */
	public static ValidationResult verifyDetails(String firstName, String lastName, String email, String phone,
			String country, String password, String confirmPassword, String profilePicture) {
		if (isNullOrEmpty(firstName)) {
			return new ValidationResult(false, "First name required");
		}
		if (!isAlphabetic(firstName)) {
			return new ValidationResult(false, "First name invalid");
		}
		if (isNullOrEmpty(lastName)) {
			return new ValidationResult(false, "Last name required");
		}
		if (!isAlphabetic(lastName)) {
			return new ValidationResult(false, "Last name invalid");
		}
		if (!isValidEmail(email)) {
			return new ValidationResult(false, "Invalid email");
		}
		if (!isValidPhoneNumberFormat(phone)) {
			return new ValidationResult(false, "Invalid phone number");
		}
		if (!isCountrySelected(country)) {
			return new ValidationResult(false, "Country required");
		}
		if (isNullOrEmpty(password)) {
			return new ValidationResult(false, "Password required");
		}
		if (!isValidPasswordComplexity(password)) {
			return new ValidationResult(false, "Password too weak");
		}
		if (!doPasswordsMatch(password, confirmPassword)) {
			return new ValidationResult(false, "Passwords do not match");
		}
		if (!isNullOrEmpty(profilePicture)) {
			String fileName = profilePicture.toLowerCase();
			if (!(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
					|| fileName.endsWith(".gif") || fileName.endsWith(".webp") || fileName.endsWith(".bmp"))) {
				return new ValidationResult(false, "Profile picture must be an image");
			}
		}
		return new ValidationResult(true, "Valid");
	}

	/**
	 * Overloaded version for Part (file upload) use-case. This method validates
	 * details when the profile picture is provided as a Part object.
	 *
	 * @param firstName       The first name.
	 * @param lastName        The last name.
	 * @param email           The email address.
	 * @param phone           The phone number.
	 * @param country         The selected country.
	 * @param password        The password.
	 * @param confirmPassword The confirm password.
	 * @param profilePicture  The profile picture file (Part object).
	 * @return A ValidationResult object with the validation result and message.
	 */
	public static ValidationResult verifyDetails(String firstName, String lastName, String email, String phone,
			String country, String password, String confirmPassword, Part profilePicture) {
		if (isNullOrEmpty(firstName)) {
			return new ValidationResult(false, "First name required");
		}
		if (!isAlphabetic(firstName)) {
			return new ValidationResult(false, "First name invalid");
		}
		if (isNullOrEmpty(lastName)) {
			return new ValidationResult(false, "Last name required");
		}
		if (!isAlphabetic(lastName)) {
			return new ValidationResult(false, "Last name invalid");
		}
		if (!isValidEmail(email)) {
			return new ValidationResult(false, "Invalid email");
		}
		if (!isValidPhoneNumberFormat(phone)) {
			return new ValidationResult(false, "Invalid phone number");
		}
		if (!isCountrySelected(country)) {
			return new ValidationResult(false, "Country required");
		}
		if (isNullOrEmpty(password)) {
			return new ValidationResult(false, "Password required");
		}
		if (!isValidPasswordComplexity(password)) {
			return new ValidationResult(false, "Password too weak");
		}
		if (!doPasswordsMatch(password, confirmPassword)) {
			return new ValidationResult(false, "Passwords do not match");
		}
		if (!isValidImageFile(profilePicture)) {
			return new ValidationResult(false, "Profile picture must be an image");
		}
		// Optional file size check (e.g., max 2MB = 2*1024*1024)
		if (!isValidFileSize(profilePicture, 2 * 1024 * 1024)) {
			return new ValidationResult(false, "Profile picture too large");
		}
		return new ValidationResult(true, "Valid");
	}
}