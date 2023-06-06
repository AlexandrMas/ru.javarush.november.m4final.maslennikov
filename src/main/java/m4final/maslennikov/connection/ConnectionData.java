package m4final.maslennikov.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionData {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/world?createDatabaseIfNotExist=true";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "qwerty";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
