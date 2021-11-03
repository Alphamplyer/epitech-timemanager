package edu.epitech.timemanager.configurations.security;

import edu.epitech.timemanager.domains.models.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    Role role;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Role role) {
        super(username, password, authorities);
        this.role = role;
    }

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Role role) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
