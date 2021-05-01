package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDAO;
import web.dao.UserDAO;
import web.dao.UserDAOImpl;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDao;
    @Autowired
    private RoleDAO roleDao;

    @Override
    public void add(User user) {
        System.out.println("New user: "+user.getEmail()+", "+
                user.getPassword()+", "+
                user.getFirstName()+", "+
                user.getLastName()+", "+
                user.getId()+", "+
                user.getRoles().toString());
        User userFromDB = userDao.findByEmail(user.getUsername());
        if (userFromDB != null) {
            return;
        }
        user.setRoles(Collections.singleton(roleDao.findByRole("user")));

        //user.setPassword(user.getEmail());
        userDao.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateById(Long id, User user) {
        userDao.update(id, user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
