package com.aspectworks.active24;

import java.sql.*;

public class DBConnector {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static Connection conn;
    static final String USER = "admin";
    static final String PASS = "admin";
    public void Connect() throws ClassNotFoundException, SQLException {
        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        Statement s = conn.createStatement();
        ResultSet result = s.executeQuery("SELECT * FROM DUAL");
        while(result.next())
            System.out.println(result.getString(1));
        s.close();
        conn.close();

    }
}
