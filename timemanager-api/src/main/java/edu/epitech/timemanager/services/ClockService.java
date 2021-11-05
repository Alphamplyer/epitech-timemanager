package edu.epitech.timemanager.services;

import edu.epitech.timemanager.domains.models.Clock;

public interface ClockService {
    Clock getUserClock(int userId);
    Clock toggleUserClock(int userId);
}
