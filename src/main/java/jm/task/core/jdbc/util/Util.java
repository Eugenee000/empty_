package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/javastudy";
    private final static String DB_USER_NAME = "root";
    private final static String DB_PASSWORD = "1123(123*";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
        return connection;
    }
}
