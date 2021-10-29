package edu.epitech.gotham.services;

import edu.epitech.gotham.models.User;
import edu.epitech.gotham.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent())
            user = existingUser.get();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent())
            user = existingUser.get();
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        Optional<User> existingUser = userRepository.findByEmail(username);
        if (existingUser.isPresent())
            user = existingUser.get();
        return user;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, int id) {
        userRepository.update(user, id);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
