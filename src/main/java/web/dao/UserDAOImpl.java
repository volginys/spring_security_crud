package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User as u LEFT JOIN FETCH u.roles",User.class).getResultList()
                .stream().distinct().collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        return entityManager
                .createQuery("FROM User as u LEFT JOIN FETCH u.roles WHERE u.id =:id ",User.class)
                .setParameter("id", id ).getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
        return entityManager
                .createQuery("FROM User as u LEFT JOIN FETCH u.roles WHERE u.email =:email",User.class)
                .setParameter("email", email ).getSingleResult();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

}
