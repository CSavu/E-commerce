package com.example.ecommerce.services;

import at.favre.lib.crypto.bcrypt.*;

import static com.example.ecommerce.models.User.setUserId;
import static com.example.ecommerce.models.User.setUserName;

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
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
// result.verified == true
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
