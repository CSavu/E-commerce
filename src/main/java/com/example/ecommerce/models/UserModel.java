package com.example.ecommerce.models;

public class UserModel{
    private static String userName;
    private static String userId;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserModel.userName = userName;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserModel.userId = userId;
    }
}
