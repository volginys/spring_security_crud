package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {

    void add(User user);

    List<User> getAllUsers();

    User findById(Long id);

    User findByEmail(String email);

    void update(Long id, User user);

    void delete(Long id);


}
