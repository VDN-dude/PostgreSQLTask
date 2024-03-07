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
    private static final String CHECK_USAGE_OF_PHONE_NUMBER = "SELECT u.id FROM users u WHERE phone_number = ?";

    private JDBCUserDAO() {
    }

    public static JDBCUserDAO getInstance() {
        if (instance == null) {
            instance = new JDBCUserDAO();
        }
        return instance;
    }

    @Override
    public boolean save(User user) {
        boolean saved = false;

        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection(Connection.TRANSACTION_READ_UNCOMMITTED);
             PreparedStatement checkStatement = postgresConnection.prepareStatement(CHECK_USAGE_OF_PHONE_NUMBER);
             PreparedStatement saveStatement = postgresConnection.prepareStatement(INSERT)) {

            checkStatement.setString(1, user.getPhoneNumber());
            ResultSet resultSet = checkStatement.executeQuery();

            if (!resultSet.next()) {
                saveStatement.setString(1, user.getFirstname());
                saveStatement.setString(2, user.getLastname());
                saveStatement.setInt(3, user.getAge());
                saveStatement.setString(4, user.getPhoneNumber());
                saveStatement.execute();
                saved = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saved;
    }

    @Override
    public Optional<PageableUser> findAll(int offset, int pageSize) {
        List<User> userList = new ArrayList<>();
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection(Connection.TRANSACTION_READ_COMMITTED);
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_PAGEABLE_USERS)) {
            long countOfUsers = countAllUsers();

            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                userList.add(user);
            }
            PageableUser pageableUser = createPageableUser(userList, pageSize, countOfUsers);
            return Optional.of(pageableUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(long id) {
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection(Connection.TRANSACTION_READ_COMMITTED);
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_BY_ID)) {

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
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection(Connection.TRANSACTION_READ_COMMITTED);
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
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection(Connection.TRANSACTION_READ_UNCOMMITTED);
             PreparedStatement preparedStatement = postgresConnection.prepareStatement(DELETE_BY_ID)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long countAllUsers(){
        long countOfUsers = 0;
        try (Connection postgresConnection = ConnectionJDBC.getPostgresConnection(Connection.TRANSACTION_READ_COMMITTED);
             Statement statement = postgresConnection.createStatement()) {

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

    private PageableUser createPageableUser(List<User> userList, int pageSize, double countUsers){
        PageableUser pageableUser = new PageableUser();
        pageableUser.setSize(pageSize);
        pageableUser.setUserList(userList);

        long countOfPages = (long) Math.ceil(countUsers / pageSize);
        pageableUser.setCountOfPages(countOfPages);
        return pageableUser;
    }
}
