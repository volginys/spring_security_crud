package web.dao;

import web.model.Role;

public interface RoleDAO {

    Role findById(Long id);

    Role findByRole(String role);
}
