package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import javax.persistence.TypedQuery;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public Role findById(Long id) {
        return sessionFactory.getCurrentSession().find(Role.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public Role findByRole(String role) {
        TypedQuery<Role> query=sessionFactory.getCurrentSession().createQuery("from Role where role =:role")
                .setParameter("role", role );
        return query.getResultList().stream().findFirst().get();
    }
}
