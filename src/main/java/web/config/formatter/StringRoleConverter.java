package web.config.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.service.RoleService;

@Component
public class StringRoleConverter implements Converter<String, Role> {

    private RoleService roleService;

    public StringRoleConverter(@Autowired RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(String source) {
        return roleService.findRoleById(Long.parseLong(source));
    }

}