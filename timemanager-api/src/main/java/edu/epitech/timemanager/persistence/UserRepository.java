package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
}
