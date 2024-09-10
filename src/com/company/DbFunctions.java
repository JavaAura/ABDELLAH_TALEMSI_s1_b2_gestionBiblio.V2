package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbFunctions {
    private static DbFunctions instance = null;
    private static Connection con = null;
    private DbFunctions() {}

    public static DbFunctions getInstance() {
        if (instance == null) {
            synchronized (DbFunctions.class) {
                if (instance == null) {
                    instance = new DbFunctions();
                }
            }
        }
        return instance;
    }
    public Connection connect_to_db(String dbname, String user, String pass) {
        if (con == null) {
            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
                if (con != null) {
                    System.out.println("Connected to PostgreSQL database");
                } else {
                    System.out.println("Failed to connect to PostgreSQL database");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return con; // Return the existing connection
    }
    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
                con = null; // Reset connection to null for future use
                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
