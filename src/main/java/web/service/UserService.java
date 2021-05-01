package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> getAllUsers();

    void updateById(Long id, User user);

    User findById(Long id);

    void delete(Long id);

    User findByEmail(String email);
}
