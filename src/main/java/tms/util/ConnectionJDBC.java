package tms.util;

import org.postgresql.core.TransactionState;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    private ConnectionJDBC() {
    }

    public static Connection getPostgresConnection(){
        Connection connection;
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.74:5432/postgres_task", "postgres", "!QAZxsw2#EDC");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static Connection getPostgresConnection(int level){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.74:5432/postgres_task", "postgres", "!QAZxsw2#EDC");
            connection.setTransactionIsolation(level);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
