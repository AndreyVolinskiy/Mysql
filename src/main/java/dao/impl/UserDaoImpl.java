package dao.impl;

import dao.UserDao;
import data.Database;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String SELECT = "SELECT * FROM users;";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String INSERT = "INSERT INTO users (name) VALUE (?)";
    private static final String UPDATE_NAME = "UPDATE users SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id=?";


    @Override
    public User findById(Long id) {

        User user = new User();
        try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID))
        {
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new LinkedList<>();
        try (final Connection connection = Database.getConnection();
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                final User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean save(User user) {

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, user.getName());

            if (statement.executeUpdate() != 0) {
                System.out.println("User added to database.");
                return true;
            } else {
                System.out.println("DB Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(String newName, Long id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NAME)) {
            statement.setString(1, newName);
            statement.setLong(2, id);

            if (statement.executeUpdate() != 0) {
                System.out.println("New name updated.");
                return true;
            } else {
                System.out.println("Error, old name saved.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try( Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);

            if (statement.executeUpdate() != 0) {
                System.out.println("User with id = " + id + " deleted.");
                return true;
            } else {
                System.out.println("Error occurred, user is steal in db.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
