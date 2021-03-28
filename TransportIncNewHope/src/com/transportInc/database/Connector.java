package com.transportInc.database;

import java.sql.*;

public class Connector {
    private static final String URL = "jdbc:mysql://localhost:3306/transport_inc_new_hope";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connector connector;
    private Connection connection;

    private Connector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException exception) {
            System.out.println("Class not found " + exception.getMessage());
        } catch (SQLException exception) {
            System.out.println("connection could not be made " + exception.getMessage());
        }

    }

    public static Connector getConnector() {
        if (connector == null) {
            connector = new Connector();
        }
        return connector;
    }

    public Connection getConnection() {
        return connection;
    }

}