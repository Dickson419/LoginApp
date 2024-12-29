package com.example.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    public Connection dataBaseLink;

    public Connection getConnection(){
        String databaseName = "TetCorp";
        String databaseUser = "root"; //what you use to log into your SQL
        String databasePassword = "12345678";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataBaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
    }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dataBaseLink;

        }
}
