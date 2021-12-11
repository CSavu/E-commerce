package com.example.ecommerce.services;

import com.example.ecommerce.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.ecommerce.utils.database.DatabaseQueries.getQuery;
import static com.example.ecommerce.utils.database.DatabaseQueriesNames.GET_ALL_PRODUCTS;

public class ProductsService {
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

    public static List<Product> getAllProducts() throws SQLException {
        ps = conn.prepareStatement(getQuery(GET_ALL_PRODUCTS));
        rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Double price = rs.getDouble("price");
            Product product = new Product(id, name, price, description);
            products.add(product);
            System.out.println(id + name + description + price);
        }
        return products;
    }

}
