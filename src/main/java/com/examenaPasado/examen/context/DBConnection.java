package com.redSocial.redSocial.manager.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;
    //private static Connection connectiontest;

    private DBConnection(){}


    public static Connection getInstance() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://dbvoleyball.c9cfw7ltvifo.us-east-1.rds.amazonaws.com:3306/alexistweeter",
                        "vbadmin", "18069929");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    /*
    public static Connection getInstance(){
        if(connectiontest == null) {
            try {
                connectiontest = DriverManager.getConnection(
                        "jdbc:mysql://dbvoleyball.c9cfw7ltvifo.us-east-1.rds.amazonaws.com:3306/alexistweetertest",
                        "vbadmin", "18069929");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connectiontest;
    }
    */
}