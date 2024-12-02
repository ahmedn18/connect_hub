package com.connecthub.useraccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The `UserManager` class provides methods for managing user accounts, including sign-up, login, logout, and checking if a user is online.
 * It interacts with the `User` class to perform these operations and stores user data in a map.
 */
public class UserManager {
    // Map to store user IDs and corresponding `User` objects
    private Map<String, User> userMap;

    /**
     * Constructs a `UserManager` instance and initializes the user map.
     * Loads users from a JSON file.
     */
    public UserManager() {
        this.userMap = new HashMap<>();
        loadUsersFromJson("/home/ahmednader/Desktop/University/Year 3/semester_05/programming_2/assignments/connect_hub/src/com/connecthub/files/users.json");
    }

    /**
     * Loads users from a JSON file and populates the user map.
     *
     * @param filePath the path to the JSON file containing user data
     */
    private void loadUsersFromJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userMap = objectMapper.readValue(new File(filePath), new TypeReference<Map<String, User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current user map to the specified JSON file.
     *
     * @param filePath the path to the JSON file where the user data will be saved
     */
    public void saveUsersToJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Signs up a new user with the given user ID and password.
     *
     * @param userId   the user ID of the new user
     * @param password the password of the new user
     * @return true if the sign-up is successful, false if the user ID already exists
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    public boolean signUp(String userId, String password) throws NoSuchAlgorithmException {
        if (userMap.containsKey(userId)) {
            return false;
        }
        User newUser = new User(userId, "defaultUserName", "defaultEmail", new Date(), false, password);
        userMap.put(userId, newUser);
        return true;
    }

    /**
     * Logs in a user with the given user ID and password.
     *
     * @param userId   the user ID of the user
     * @param password the password of the user
     * @return true if the login is successful, false if the user ID or password is incorrect
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    public boolean login(String userId, String password) throws NoSuchAlgorithmException {
        User user = userMap.get(userId);
        if (user != null && user.verifyPassword(password)) {
            user.setOnline(true);
            return true;
        }
        return false;
    }

    /**
     * Logs out a user with the given user ID.
     *
     * @param userId the user ID of the user
     * @return true if the logout is successful, false if the user ID does not exist
     */
    public boolean logout(String userId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.setOnline(false);
            return true;
        }
        return false;
    }

    /**
     * Checks if a user with the given user ID is online.
     *
     * @param userId the user ID of the user
     * @return true if the user is online, false if the user is offline or the user ID does not exist
     */
    public boolean isUserOnline(String userId) {
        User user = userMap.get(userId);
        return user != null && user.isOnline();
    }

    /**
     * Retrieves the user with the specified user ID.
     *
     * @param userId the user ID of the user to retrieve
     * @return the `User` object corresponding to the given user ID, or null if the user does not exist
     */
    public User getUserWithId(String userId) {
        return userMap.get(userId);
    }

}