package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query=sessionFactory.getCurrentSession()
                .createQuery("from User where email =:email")
                .setParameter("email", email );
        return query.getResultList().stream().findFirst().get();
    }

    @Transactional
    @Override
    public void update(Long id, User user) {
        User user1 = sessionFactory.getCurrentSession().find(User.class, id);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        sessionFactory.getCurrentSession().update(user1);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = sessionFactory.getCurrentSession().find(User.class, id);
        sessionFactory.getCurrentSession().delete(user);
    }

}
