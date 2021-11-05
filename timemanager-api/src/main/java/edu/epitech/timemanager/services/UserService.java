package edu.epitech.timemanager.services;

import edu.epitech.timemanager.domains.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(int id);
    User getUser(String username);
    User getUserByUsernameOrEmail(String username, String email);

    User createUser(User user);
    User updateUser(int id, User user);
    void deleteUser(int id);
}
