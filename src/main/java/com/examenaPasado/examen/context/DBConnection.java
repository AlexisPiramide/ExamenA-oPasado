package com.examenaPasado.examen.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private DBConnection(){}

    public static Connection getInstance() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://baseprueba.cfekksgu4bcu.us-east-1.rds.amazonaws.com:3306/portfolio",
                        "admin", "18175118N");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}