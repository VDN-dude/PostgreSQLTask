package tms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    private ConnectionJDBC() {
    }

    public static Connection getPostgresConnection(){
        Connection connection;
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
