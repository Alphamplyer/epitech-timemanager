package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.domains.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClockRepository extends JpaRepository<Clock, Integer> {
    Optional<Clock> findClockByUser(User user);
}
