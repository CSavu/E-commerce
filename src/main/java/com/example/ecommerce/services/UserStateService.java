package com.example.ecommerce.services;

import com.example.ecommerce.models.UserModel;

import static com.example.ecommerce.models.UserModel.setUserId;
import static com.example.ecommerce.models.UserModel.setUserName;

public class UserStateService {

    /**
     * Sets the state of the entered user (loggedIn, userName, userId, etc.)
     *
     * @param userName
     * @param password
     * @return success or failure (true/false) of setting the state of the entered user
     */
    public static boolean setCurrentUser(String userName, String password){
        if (validate(userName, password)) {
            // query to DB to get user id & data
            // set UserModel to what was taken from DB
            setUserName(userName);
            setUserId("1");
            return true;
        }
        return false;
    }

    private static boolean validate(String userName, String password){
        if (true){
            return true;
        }
        return false;
    }

}
