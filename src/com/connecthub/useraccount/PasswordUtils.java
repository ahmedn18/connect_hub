package com.connecthub.useraccount;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
/**
 * The `PasswordUtils` class provides utility methods for handling password security.
 * It includes methods for generating a random salt, hashing a password with a salt, and verifying a password against a hashed password.
 *
 * <p>Example usage:</p>
 * <pre>
 *     String salt = PasswordUtils.generateSalt();
 *     String hashedPassword = PasswordUtils.hashPassword("password123", salt);
 *     boolean isPasswordCorrect = PasswordUtils.verifyPassword("password123", hashedPassword, salt);
 * </pre>
 *
 * <p>Note: The password is hashed using the SHA-256 algorithm and the salt is encoded in Base64 format for storage.</p>
 *
 * @author Ahmed Nader
 */
public class PasswordUtils {
    /**
     * Generates a random salt for password hashing.
     *
     * @return the generated salt as a Base64 encoded string
     */
    public static String generateSalt() {
        // Create a secure random number generator
        SecureRandom random = new SecureRandom();

        // Generate a random salt of 16 bytes
        byte[] salt = new byte[16];

        // Fill the salt with random bytes
        random.nextBytes(salt);

        // Return the salt as a Base64 encoded string (to store it as a string in the database)
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hashes the given password using the specified salt.
     *
     * @param password the password to hash
     * @param salt the salt to add to the password before hashing
     * @return the hashed password as a Base64 encoded string
     */
    protected static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        // Concatenate the password and salt
        String passwordWithSalt = password + salt;

        // Creates a SHA-256 message digest instance
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Generate the hashed password bytes
        byte[] hashedBytes = messageDigest.digest(passwordWithSalt.getBytes());

        // Return the hashed password as a Base64 encoded string (because the hashed bytes may contain non-printable characters)
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    /**
     * Verifies the given password against the hashed password using the specified salt.
     *
     * @param password the password to verify
     * @param hashedPassword the hashed password to compare against
     * @param salt the salt used to hash the password
     * @return true if the password is correct, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword, String salt) throws NoSuchAlgorithmException {
        String newHashedPassword = hashPassword(password, salt);
        return newHashedPassword.equals(hashedPassword);
    }
}
