package utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Utility class for securely hashing and verifying passwords using PBKDF2.
 * Implements password hashing with salt for secure storage.
 * Does NOT provide reversible encryption (passwords cannot be decrypted).
 */
public class PasswordUtil {
    private static final int SALT_LENGTH_BYTE = 16;
    private static final int HASH_LENGTH_BYTE = 32; // 256 bits
    private static final int ITERATIONS = 65536;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final Logger LOGGER = Logger.getLogger(PasswordUtil.class.getName());
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * Generates a cryptographically secure random salt.
     * 
     * @param numBytes The length of the salt in bytes
     * @return Random salt as byte array
     */
    private static byte[] getRandomSalt(int numBytes) {
        byte[] salt = new byte[numBytes];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    /**
     * Hashes a password with a random salt using PBKDF2WithHmacSHA256.
     * 
     * @param password The plaintext password to hash
     * @return String in format "salt:hash" (both Base64 encoded) or null if hashing fails
     */
    public String hashPassword(String password) {
        try {
            byte[] salt = getRandomSalt(SALT_LENGTH_BYTE);
            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                HASH_LENGTH_BYTE * 8
            );
            
            SecretKeyFactory skf = SecretKeyFactory.getInstance(HASH_ALGORITHM);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            
            return Base64.getEncoder().encodeToString(salt) + ":" + 
                   Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.log(Level.SEVERE, "Password hashing failed", e);
            return null;
        }
    }

    /**
     * Verifies a password against a stored hash.
     * 
     * @param password The plaintext password to check
     * @param stored The stored password hash in "salt:hash" format
     * @return true if password matches the hash, false otherwise
     */
    public boolean verifyPassword(String password, String stored) {
        try {
            String[] parts = stored.split(":");
            if (parts.length != 2) {
                return false;
            }
            
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[1]);

            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                expectedHash.length * 8
            );
            
            SecretKeyFactory skf = SecretKeyFactory.getInstance(HASH_ALGORITHM);
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Constant-time comparison to prevent timing attacks
            if (hash.length != expectedHash.length) {
                return false;
            }
            
            int result = 0;
            for (int i = 0; i < hash.length; i++) {
                result |= hash[i] ^ expectedHash[i];
            }
            
            return result == 0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Password verification failed", e);
            return false;
        }
    }
}