package com.example.ecommerce.services;

import java.sql.*;

public class UserService {
    private static String connectionUrl = "jdbc:mysql://localhost:3306/products?user=root";
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;

    static {
        try {
            conn = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
