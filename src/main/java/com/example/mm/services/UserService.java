package com.example.mm.services;

import java.sql.*;

import static com.example.mm.models.User.getUserId;
import static com.example.mm.utils.ServiceUtils.checkMatchingPasswords;
import static com.example.mm.utils.ServiceUtils.hashPassword;
import static com.example.mm.utils.database.DatabaseQueries.getQuery;
import static com.example.mm.utils.database.DatabaseQueriesNames.*;

public class UserService {
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/ecommerce?user=root";
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

    public static Long validateUserOnSignIn(String username, String password) throws SQLException {
        ps = conn.prepareStatement(String.format(getQuery(GET_USER_ID_AND_PASSWORD_FROM_USERNAME), username));
        rs = ps.executeQuery();
        Long retrievedId = null;
        String retrievedPass = null;
        while (rs.next()) {
            retrievedId = rs.getLong("user_id");
            retrievedPass = rs.getString("password");
        }
        System.out.println(password + " " + retrievedPass);
        if (retrievedId != null && checkMatchingPasswords(password, retrievedPass) == true){
            return retrievedId;
        }
        return null;
    }


    public static boolean buildUser(String username, String password) throws SQLException {
        ps = conn.prepareStatement(String.format(getQuery(BUILD_USER_FROM_USERNAME_AND_PASSWORD), username, hashPassword(password)));
        ps.execute();
        Long retrievedId = validateUserOnSignIn(username, password);
        if (retrievedId != null) return true;
        return false;
    }

    public static Long getCartIdForUser() throws SQLException {
        Long currentUserId = getUserId();
        if (currentUserId != null) {
            ps = conn.prepareStatement(String.format(getQuery(GET_LAST_CART_ID_FOR_USER), currentUserId));
            rs = ps.executeQuery();
            Long retrievedId = null;
            while (rs.next()) {
                retrievedId = rs.getLong("cart_id");
            }
            return retrievedId;
        }
        return null;
    }

    public static boolean buildUserInvoice(String name, String surname, String address, String phone) {
        try {
            Long currentCartId = getCartIdForUser();
            ps = conn.prepareStatement(String.format(getQuery(BUILD_USER_INVOICE), currentCartId, name + surname, address, phone));
            ps.execute();
            return true;
        } catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

}
