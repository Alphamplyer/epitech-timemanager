package edu.epitech.timemanager.configurations.security;

import edu.epitech.timemanager.domains.models.enumerations.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class RoleGrantedPermissions {
    public static List<SimpleGrantedAuthority> getPermissions(Role role) {
        switch (role) {
            case EMPLOYEE:
                return List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
            case MANAGER:
                return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"));
            case GLOBAL_MANAGER:
                return List.of(new SimpleGrantedAuthority("ROLE_GLOBAL_MANAGER"));
            default:
                return List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        }
    }
}
