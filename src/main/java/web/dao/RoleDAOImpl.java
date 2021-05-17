package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class)
                .getResultList().stream()
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Role findRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
