package utils;

import java.util.regex.Pattern;
import jakarta.servlet.http.Part;

public class ValidationUtils {

    /**
     * Checks if a string is null, empty, or contains only whitespace.
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Checks if a string contains only letters.
     */
    public static boolean isAlphabetic(String value) {
        return value != null && value.matches("^[a-zA-Z]+$");
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPhoneNumberFormat(String phoneNumber) {
        if (isNullOrEmpty(phoneNumber)) {
            return true;
        }
        String phoneRegex = "^\\+?[0-9\\s\\-\\(\\)]+$";
        return phoneNumber.matches(phoneRegex);
    }

    public static boolean isValidPasswordComplexity(String password) {
        if (isNullOrEmpty(password)) {
            return false;
        }
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }

    public static boolean doPasswordsMatch(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }

    public static boolean isCountrySelected(String countryValue) {
        return !isNullOrEmpty(countryValue);
    }

    public static boolean isValidImageFile(Part imagePart) {
        if (imagePart == null || imagePart.getSize() == 0 || isNullOrEmpty(imagePart.getSubmittedFileName())) {
            return true;
        }
        String fileName = imagePart.getSubmittedFileName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
            || fileName.endsWith(".gif") || fileName.endsWith(".webp") || fileName.endsWith(".bmp");
    }

    public static boolean isValidFileSize(Part imagePart, long maxSizeInBytes) {
        if (imagePart == null || imagePart.getSize() == 0) {
            return true;
        }
        return imagePart.getSize() <= maxSizeInBytes;
    }

    /**
     * Verifies form details, returning ValidationResult with success and a short message.
     * profilePicture can be a filename (String) or you can overload for Part.
     * Now includes password and confirmPassword checks.
     */
    public static ValidationResult verifyDetails(
        String firstName,
        String lastName,
        String email,
        String phone,
        String country,
        String password,
        String confirmPassword,
        String profilePicture
    ) {
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
     * Overloaded version for Part (file upload) use-case.
     * Now includes password and confirmPassword checks.
     */
    public static ValidationResult verifyDetails(
        String firstName,
        String lastName,
        String email,
        String phone,
        String country,
        String password,
        String confirmPassword,
        Part profilePicture
    ) {
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