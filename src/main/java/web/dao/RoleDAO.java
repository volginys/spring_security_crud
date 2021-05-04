package web.dao;

import web.model.Role;
import java.util.Set;


public interface RoleDAO {

    Set<Role> getAllRoles();

    Role findRoleById(Long id);
}
