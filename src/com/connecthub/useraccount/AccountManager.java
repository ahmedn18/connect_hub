package com.connecthub.useraccount;

import java.security.NoSuchAlgorithmException;

/**
 * The `AccountManager` class provides methods for managing user accounts, including sign-up, login, logout, and checking if a user is online.
 * It interacts with the `UserManager` class to perform these operations.
 */
public class AccountManager {

    private UserManager userManager;

    /**
     * Constructs an `AccountManager` instance and initializes the `UserManager`.
     */
    public AccountManager() {
        this.userManager = new UserManager();
    }

    /**
     * Signs up a new user with the given user ID and password.
     *
     * @param userId the user ID of the new user
     * @param password the password of the new user
     * @return true if the sign-up is successful, false if the user ID already exists or an error occurs
     */
    public boolean signUp(String userId, String password) {
        try {
            return userManager.signUp(userId, password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Logs in a user with the given user ID and password.
     *
     * @param userId the user ID of the user
     * @param password the password of the user
     * @return true if the login is successful, false if the user ID or password is incorrect or an error occurs
     */
    public boolean login(String userId, String password) {
        try {
            return userManager.login(userId, password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Logs out a user with the given user ID.
     *
     * @param userId the user ID of the user
     * @return true if the logout is successful, false if the user ID does not exist
     */
    public boolean logout(String userId) {
        return userManager.logout(userId);
    }

    /**
     * Checks if a user with the given user ID is online.
     *
     * @param userId the user ID of the user
     * @return true if the user is online, false if the user is offline or the user ID does not exist
     */
    public boolean isUserOnline(String userId) {
        return userManager.isUserOnline(userId);
    }
}