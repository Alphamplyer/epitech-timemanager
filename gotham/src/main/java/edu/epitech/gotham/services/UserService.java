package edu.epitech.gotham.services;

import edu.epitech.gotham.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);

    void createUser(User user);
    void updateUser(User user, int id);
    void deleteUser(int id);

}
