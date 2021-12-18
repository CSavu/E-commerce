package com.example.mm.utils.database;

import java.util.Map;

import static com.example.mm.utils.database.DatabaseQueriesNames.*;

/**
 * Map for holding all the necessary DB queries.
 * Used in ... for obtaining the queries,
 * and then interpolating them with the necessary data, before sending them to the DB.
 */
public class DatabaseQueries {
    private static Map<DatabaseQueriesNames, String> queries = Map.ofEntries(
            Map.entry(GET_ALL_PRODUCTS, "SELECT * FROM products"),
            Map.entry(GET_ALL_USERS, "SELECT * FROM users"),
            Map.entry(GET_USERID_FROM_USERNAME, "SELECT user_id FROM users WHERE username='%s'"),
            Map.entry(GET_ALL_PRODUCTS_BY_PRICE_ASC, "SELECT * FROM products ORDER BY price ASC"),
            Map.entry(GET_ALL_PRODUCTS_BY_PRICE_DESC, "SELECT * FROM products ORDER BY price DESC"),
            Map.entry(GET_USER_ID_AND_PASSWORD_FROM_USERNAME, "SELECT user_id, password FROM users WHERE user_name='%s'"),
            Map.entry(BUILD_USER_FROM_USERNAME_AND_PASSWORD, "INSERT INTO users(user_name, password) VALUES('%s', '%s')"),
            Map.entry(GET_PRODUCTS_BY_NAME, "SELECT * FROM products WHERE product_name LIKE '%%s%'"),
            Map.entry(GET_PRODUCTS_FOR_USER, "SELECT p.product_id FROM users JOIN carts c on users.user_id = c.user_id JOIN carts_details cd on c.cart_id = cd.cart_id JOIN products p on cd.product_id = p.product_id\n" +
                    "WHERE users.user_id=%d"),
            Map.entry(GET_LAST_CART_ID_FOR_USER, "SELECT cart_id FROM carts WHERE user_id='%s' ORDER BY cart_id DESC LIMIT 1"),
            Map.entry(BUILD_USER_INVOICE, "INSERT INTO invoices(cart_id, name, address, phone) VALUES(%d, '%s', '%s', '%s')"),
            Map.entry(BUILD_NEW_CART_FOR_USER, "INSERT INTO carts(user_id) VALUES(%d)"),
            Map.entry(ADD_PRODUCT_TO_CART, "INSERT INTO carts_details(cart_id, product_id, quantity) VALUES(%d, %d, %d)")
    );

    public static String getQuery(DatabaseQueriesNames databaseQueriesName) {
        return queries.get(databaseQueriesName);
    }
}

