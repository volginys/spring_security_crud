package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDAO;
import web.model.Role;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDao;

    public RoleServiceImpl(@Autowired RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional
    @Override
    public Role findRoleById(Long id) {
        return roleDao.findRoleById(id);
    }
}
