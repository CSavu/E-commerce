package com.example.mm.services;

import java.sql.SQLException;

import static com.example.mm.models.User.setUserId;
import static com.example.mm.services.UserService.validateUserOnSignIn;

/**
 * TODO: Add try-catch in all SQL-related methods!
 */
public class UserStateService {

    /**
     * Sets the state (userId) of the current user.
     *
     * @param username  - given username
     * @param password  - given password
     * @return success or failure (true/false) of setting the state of the entered user
     */
    public static boolean setCurrentUser(String username, String password) throws SQLException {
        Long userId = validateUserOnSignIn(username, password);
        if (userId != null){
            setUserId(userId);
            return true;
        }
        return false;
    }


}