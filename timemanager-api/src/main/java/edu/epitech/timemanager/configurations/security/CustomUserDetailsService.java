package edu.epitech.timemanager.configurations.security;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.shared.exceptions.ApiErrorException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user;

        try {
            Optional<User> existingUser = userRepository.findUserByUsernameOrEmail(identifier, identifier);

            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                throw new BadCredentialsException("User not found");
            }
        } catch (HibernateException e) {
            throw new ApiErrorException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        List<SimpleGrantedAuthority> roles = RoleGrantedPermissions.getPermissions(user.getRole());
        return new CustomUserDetails(user.getEmail(), user.getHashedPassword(), roles, user.getRole());
    }
}
