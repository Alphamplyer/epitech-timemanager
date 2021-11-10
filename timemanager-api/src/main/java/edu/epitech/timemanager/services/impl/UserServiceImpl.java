package edu.epitech.timemanager.services.impl;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.persistence.TeamRepository;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User getUserByUsernameOrEmail(String username, String email) {
        if (username != null && !username.isEmpty() && email != null && !email.isEmpty()) {
            return userRepository.findByUsernameOrEmail(username, email).orElse(null);
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        User persistedUser = userRepository.findById(id).orElse(null);

        if (persistedUser == null) {
            return null;
        }

        persistedUser.setUsername(user.getUsername());
        persistedUser.setEmail(user.getEmail());

        return userRepository.save(persistedUser);
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow();
        teamRepository.deleteUserFromTeams(user.getId());
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(login, login).orElse(null);

        if (user == null) {
            log.error("User not found with username or email: {}", login);
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found with username or email: {}", login);
        }

        return user;
    }
}
