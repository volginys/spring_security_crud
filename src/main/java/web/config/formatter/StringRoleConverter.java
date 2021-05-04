package web.config.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import web.dao.RoleDAO;
import web.model.Role;
import web.service.RoleService;

@Component
public class StringRoleConverter implements Converter<String, Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public Role convert(String source) {
        return roleService.findRoleById(Long.parseLong(source));
    }

}