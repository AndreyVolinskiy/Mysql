package dao;

import model.Todo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Igor Hnes on 2/2/18.
 */
public interface TodoDao {

    Todo findById(Long id);

    List<Todo> findAll();

    boolean save(Todo todo) throws SQLException;

    boolean update(String newStatus, Long id);

    boolean delete(Long id);
}
