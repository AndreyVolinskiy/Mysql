package dao.impl;

import dao.TodoDao;
import data.Database;
import model.Todo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Igor Hnes on 2/2/18.
 */
public class TodoDaoImpl implements TodoDao {

    private static final String SELECT = "SELECT * FROM todo;";
    private static final String SELECT_BY_ID = "SELECT * FROM todo WHERE id = ?";
    private static final String INSERT = "INSERT INTO todo (name, status) VALUES (?,?)";
    private static final String UPDATE_STATUS = "UPDATE todo SET status = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM todo WHERE id = ?";

    public boolean save(Todo todo) {

        try (final Connection connection = Database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, todo.getName());
            statement.setString(2, todo.getStatus());

            if (statement.executeUpdate()!=0) {
                System.out.println("An entry added to database correctly.");
                return true;
            } else{
                System.out.println("Something went wrong.");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Todo findById(Long id) {
        final Todo todo = new Todo();
        try (final Connection connection = Database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);

            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            todo.setName(resultSet.getString("name"));
            todo.setStatus(resultSet.getString("status"));
            todo.setId(resultSet.getLong("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public boolean update(String newStatus, Long id) {
        try (final Connection connection = Database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            statement.setString(1, newStatus);
            statement.setLong(2, id);

            if (statement.executeUpdate() != 0) {
                System.out.println("Status updated successfully.");
                return true;
            } else {
                System.out.println("Error while updating db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean delete(Long id) {
        try (final Connection connection = Database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);

            if (statement.executeUpdate() != 0) {
                System.out.println("Selected entry deleted from db");
                return true;
            } else {
                System.out.println("Error when deleting");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Todo> findAll() {
        List<Todo> todos = new LinkedList<>();
        try (final Connection connection = Database.getConnection();
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                final Todo todo = new Todo();
                todo.setName(resultSet.getString("name"));
                todo.setStatus(resultSet.getString("status"));
                todos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }
}
