package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
    //JDBC Driver and DB URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=GMT";
    //mySql User and Pass
    private static final String USER = "Alex";
    private static final String PASS = "Alexdb";

    private static Connection connection;

    public DbCon() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static  Connection getConnection() {
        if (connection == null)
            new DbCon();

        return connection;
    }
}
