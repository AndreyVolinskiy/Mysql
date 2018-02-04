package service.impl;

import dao.factory.DaoFactory;
import model.Todo;
import service.TodoService;

import java.sql.SQLException;
import java.util.List;

public class TodoServiceImpl implements TodoService {

    @Override
    public boolean save(Todo todo) throws SQLException {
        return DaoFactory.getTodoDao().save(todo);
    }

    @Override
    public Todo findById(Long id) {
        return DaoFactory.getTodoDao().findById(id);
    }

    @Override
    public boolean update(String newStatus, Long id) {
        return DaoFactory.getTodoDao().update(newStatus, id);
    }

    @Override
    public boolean delete(Long id) {
        return DaoFactory.getTodoDao().delete(id);
    }

    @Override
    public List<Todo> findAll() {
        return DaoFactory.getTodoDao().findAll();
    }
}
