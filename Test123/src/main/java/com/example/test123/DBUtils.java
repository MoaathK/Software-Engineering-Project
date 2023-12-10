package com.example.test123;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:/WhereItGoesDB";
    private static final String DATABASE_USER = "";
    private static final String DATABASE_PASSWORD = "";




    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:/WhereItGoesDB", "", "");
            return connection;
        } catch (Exception ex) {
            System.out.println(ex);
            return connection;
        }
    }

}
