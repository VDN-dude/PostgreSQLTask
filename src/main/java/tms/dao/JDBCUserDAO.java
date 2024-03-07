package tms.dao;

import tms.entity.PageableUser;
import tms.entity.User;
import tms.util.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDAO implements UserDAO {
    private static JDBCUserDAO instance;
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_PAGEABLE_USERS = "SELECT * FROM users LIMIT ? OFFSET ?";
    private static final String UPDATE = "UPDATE users SET firstname = ?, lastname = ?, age = ?, phone_number = ? WHERE id = ?";
    private static final String INSERT = "INSERT INTO users (firstname, lastname, age, phone_number) VALUES (?, ?, ?, ?)";
    private static final String COUNT_ALL_USERS = "SELECT count(*) FROM users";

    private JDBCUserDAO() {
    }

    public static JDBCUserDAO getInstance() {
        if (instance == null) {
            instance = new JDBCUserDAO();
        }
        return instance;
    }

    @Override
    public void save(User user) {
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(INSERT)) {
            postgresConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PageableUser> findAll(int offset, int pageSize) {
        List<User> userList = new ArrayList<>();
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_PAGEABLE_USERS)) {
            postgresConnection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            long countOfUsers = countAllUsers();

            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                userList.add(user);
            }
            return Optional.of(createPageableGoods(userList, pageSize, countOfUsers));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(long id) {
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_BY_ID)) {
            postgresConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = buildUser(resultSet);
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void modify(User user) {
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(DELETE_BY_ID)) {
            postgresConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private long countAllUsers(){
        long countOfUsers = 0;
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
             Statement statement = postgresConnection.createStatement()) {
            postgresConnection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            ResultSet resultSet2 = statement.executeQuery(COUNT_ALL_USERS);
            if (resultSet2.next()){
                countOfUsers = resultSet2.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countOfUsers;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        int age = resultSet.getInt("age");
        String phoneNumber = resultSet.getString("phone_number");
        return new User(id, firstname, lastname, age, phoneNumber);
    }

    private PageableUser createPageableGoods(List<User> userList, int pageSize, double countUsers){
        PageableUser pageableUser = new PageableUser();
        pageableUser.setSize(pageSize);
        pageableUser.setUserList(userList);

        long countOfPages = (long) Math.ceil(countUsers / pageSize);
        pageableUser.setCountOfPages(countOfPages);
        return pageableUser;
    }
}
