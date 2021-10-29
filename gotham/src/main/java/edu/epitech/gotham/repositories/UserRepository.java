package edu.epitech.gotham.repositories;

import edu.epitech.gotham.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    int save(User user);
    int update(User user, int id);
    int delete(int id);
}
