package com.giver.lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "postgres";
    private static final String PASSWORD = "giver";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection(String db_name) {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL+db_name, USER, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
