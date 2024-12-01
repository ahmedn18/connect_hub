package com.connecthub.useraccount;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class User {
    private String userId;
    private String userName;
    private String email;
    private Date dateOfBirth;
    private boolean isOnline;
    private String password;
    private String salt; // Randomly generated string to add to password before hashing

    /**
     * Constructs a new `User` object with the specified details.
     *
     * @param userId the unique identifier for the user
     * @param userName the username of the user
     * @param email the email address of the user
     * @param dateOfBirth the date of birth of the user
     * @param isOnline the online status of the user
     * @param password the password of the user
     */
    public User(String userId, String userName, String email, Date dateOfBirth, boolean isOnline, String password) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isOnline = isOnline;
        this.salt = PasswordUtils.generateSalt();
        this.password = PasswordUtils.hashPassword(password, this.salt);
    }

    /**
     * Verifies if the provided password matches the stored hashed password.
     *
     * @param password the password to verify
     * @return true if the password matches, false otherwise
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    public boolean verifyPassword(String password) throws NoSuchAlgorithmException {
        return PasswordUtils.verifyPassword(password, this.password, this.salt);
    }

    // Getters and setters for the class fields

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = PasswordUtils.hashPassword(password, this.salt);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}