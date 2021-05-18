package web.config.formatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import web.model.Role;

@Component
public class RoleStringConverter implements Converter<Role, String> {

    @Override
    public String convert(Role role) {
        return role.getId().toString();
    }

}
