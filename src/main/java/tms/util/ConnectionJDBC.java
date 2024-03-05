package tms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static Connection getPostgresConnection(){
        Connection connection;
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.74:5432/postgres", "postgres", "!QAZxsw2#EDC");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
