package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    Optional<User> findUserByUsernameOrEmail(String username, String email);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
}
