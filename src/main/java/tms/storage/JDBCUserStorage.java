package tms.storage;

import tms.entity.User;
import tms.util.ConnectionJDBC;

import java.sql.*;
import java.util.Optional;

public class JDBCUserStorage implements UserStorage{
    private static JDBCUserStorage instance;
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String WRITE_USER = "INSERT INTO users (firstname, lastname, email, username, password) VALUES (?, ?, ?, ?, ?)";

    private JDBCUserStorage() {

    }
    public static JDBCUserStorage getInstance(){
        if (instance == null){
            instance = new JDBCUserStorage();
        }
        return instance;
    }

    public void save(User user) {
        Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(WRITE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByEmail(String email) {
        Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
        try {
            Statement statement = postgresConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while(resultSet.next()){
                if(resultSet.getString("email").equals(email)){
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    int age = resultSet.getInt("age");
                    String password = resultSet.getString("password");

                    User user = new User(id, firstName, lastName, id, email, password);
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public boolean checkEmail(String email){
        Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_ALL_USERS + " where email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
