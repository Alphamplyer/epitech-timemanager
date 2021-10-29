package edu.epitech.gotham.repositories;

import edu.epitech.gotham.models.Clock;

import java.util.Optional;

public interface ClockRepository {
    Optional<Clock> getUserClock(int userId);
    int createClock(Clock clock, int userId);
    int updateClock(Clock clock, int userId);
}
