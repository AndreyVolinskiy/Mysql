package service;

import model.Todo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Igor Hnes on 2/2/18.
 */
public interface TodoService {

    boolean save(Todo todo) throws SQLException;

    Todo findById(Long id);

    boolean update(String newStatus, Long id);

    boolean delete(Long id);

    List<Todo> findAll();
}
