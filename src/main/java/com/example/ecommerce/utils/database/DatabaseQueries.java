package com.example.ecommerce.utils.database;

import java.util.HashMap;
import java.util.Map;

import static com.example.ecommerce.utils.database.DatabaseQueriesNames.*;

/**
 * Map for holding all the necessary DB queries.
 * Used in {@link com.example.ecommerce.services.DatabaseService} for obtaining the queries,
 * and then interpolating them with the necessary data, before sending them to the DB.
 */
public class DatabaseQueries {
    private static Map<DatabaseQueriesNames, String> queries = Map.ofEntries(
            Map.entry(GET_ALL_PRODUCTS, "SELECT * FROM product"),
            Map.entry(GET_ALL_USERS, "SELECT * FROM users"),
            Map.entry(GET_USERID_FROM_USERNAME, "SELECT id FROM users WHERE username=%s")
    );

    public static String getQuery(DatabaseQueriesNames databaseQueriesName) {
        return queries.get(databaseQueriesName);
    }
}
