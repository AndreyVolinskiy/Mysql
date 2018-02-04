package service;

import model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    boolean save(User user);

    boolean update(String newName, Long id);

    boolean delete(Long id);
}
