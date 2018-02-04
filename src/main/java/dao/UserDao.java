package dao;

import model.User;

import java.util.List;

public interface UserDao {

    User findById(Long id);

    List<User> findAll();

    boolean save(User user);

    boolean update(String newName, Long id);

    boolean delete(Long id);
}
