package service.impl;

import dao.factory.DaoFactory;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User findById(Long id) {
        return DaoFactory.getUserDao().findById(id);
    }

    @Override
    public List<User> findAll() {
        return DaoFactory.getUserDao().findAll();
    }

    @Override
    public boolean save(User user) {
        return DaoFactory.getUserDao().save(user);
    }

    @Override
    public boolean update(String newName, Long id) {
        return DaoFactory.getUserDao().update(newName, id);
    }

    @Override
    public boolean delete(Long id) {
        return DaoFactory.getUserDao().delete(id);
    }
}
