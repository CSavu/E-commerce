package com.example.mm.services;

import com.example.mm.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.mm.models.User.getUserId;
import static com.example.mm.services.UserService.buildNewCartForUser;
import static com.example.mm.services.UserService.getCurrentCartIdForUser;
import static com.example.mm.utils.database.DatabaseQueries.getQuery;
import static com.example.mm.utils.database.DatabaseQueriesNames.*;

/**
 * TODO: Add try-catch in all SQL-related methods!
 *
 * TODO: Refactor getProducts methods!
 */
public class ProductsService {
    private static String connectionUrl = "jdbc:mysql://localhost:3306/ecommerce?user=root";
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

    public static List<Product> getAllProducts() throws SQLException {
        ps = conn.prepareStatement(getQuery(GET_ALL_PRODUCTS));
        rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("product_id");
            String name = rs.getString("product_name");
            String description = rs.getString("product_description");
            Double price = rs.getDouble("price");
            Product product = new Product(id, name, price, description);
            products.add(product);
            System.out.println(id + name + description + price);
        }
        return products;
    }

    public static List<Product> getAllProductsByPriceAscending() throws SQLException {
        ps = conn.prepareStatement(getQuery(GET_ALL_PRODUCTS_BY_PRICE_ASC));
        rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("product_id");
            String name = rs.getString("product_name");
            String description = rs.getString("product_description");
            Double price = rs.getDouble("price");
            Product product = new Product(id, name, price, description);
            products.add(product);
            System.out.println(id + name + description + price);
        }
        return products;
    }

    public static List<Product> getAllProductsByPriceDescending() throws SQLException {
        ps = conn.prepareStatement(getQuery(GET_ALL_PRODUCTS_BY_PRICE_DESC));
        rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("product_id");
            String name = rs.getString("product_name");
            String description = rs.getString("product_description");
            Double price = rs.getDouble("price");
            Product product = new Product(id, name, price, description);
            products.add(product);
            System.out.println(id + name + description + price);
        }
        return products;
    }

    public static List<Product> getProductsByName(String productName) throws SQLException {
        ps = conn.prepareStatement(String.format(getQuery(GET_PRODUCTS_BY_NAME), productName));
        rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("product_id");
            String name = rs.getString("product_name");
            String description = rs.getString("product_description");
            Double price = rs.getDouble("price");
            Product product = new Product(id, name, price, description);
            products.add(product);
            System.out.println(id + name + description + price);
        }
        return products;
    }

    public static List<Product> getProductsForUser() throws SQLException {
        Long currentUserId = getUserId();
        if (currentUserId != null){
            ps = conn.prepareStatement(String.format(getQuery(GET_CART_PRODUCTS_FOR_USER), currentUserId));
            rs = ps.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("product_id");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Double price = rs.getDouble("price");
                Product product = new Product(id, name, price, description);
                products.add(product);
                System.out.println(id + name + description + price);
            }
            return products;
        }
        return Collections.emptyList();
    }

    public static boolean addProductToCart(Long productId) throws SQLException {
        Long currentUserId = getUserId();
        Long currentCartId = getCurrentCartIdForUser();
        if (currentUserId != null){
            if (currentCartId != null){
                ps = conn.prepareStatement(String.format(getQuery(ADD_PRODUCT_TO_CART), currentCartId, productId, 1));
                rs = ps.executeQuery();
                return true;
            } else {
                boolean newCartResult = buildNewCartForUser();
                if (newCartResult){
                    currentCartId = getCurrentCartIdForUser();
                    ps = conn.prepareStatement(String.format(getQuery(ADD_PRODUCT_TO_CART), currentCartId, productId, 1));
                    rs = ps.executeQuery();
                    return true;
                }
            }
        }
        return false;
    }

}
