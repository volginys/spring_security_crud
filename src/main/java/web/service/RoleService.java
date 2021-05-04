package web.service;

import web.model.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> getAllRoles();

    Role findRoleById(Long id);
}
