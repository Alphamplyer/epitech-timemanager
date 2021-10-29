package edu.epitech.gotham.services;

import edu.epitech.gotham.models.Clock;

public interface ClockService {
    Clock getUserClock(int userId);
    Clock toggleUserClock(int userId);
}
