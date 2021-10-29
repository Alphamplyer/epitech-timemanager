package edu.epitech.gotham.services;

import edu.epitech.gotham.models.Clock;
import edu.epitech.gotham.models.WorkingTime;
import edu.epitech.gotham.repositories.ClockRepository;
import edu.epitech.gotham.repositories.WorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class ClockServiceImpl implements ClockService {

    private final ClockRepository clockRepository;
    private final WorkingTimeRepository workingTimeRepository;

    @Autowired
    public ClockServiceImpl(ClockRepository clockRepository, WorkingTimeRepository workingTimeRepository) {
        this.clockRepository = clockRepository;
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public Clock getUserClock(int userId) {
        Clock clock = new Clock();
        Optional<Clock> existingWorkingTime = clockRepository.getUserClock(userId);
        if (existingWorkingTime.isPresent())
            clock = existingWorkingTime.get();
        return clock;
    }

    @Override
    public Clock toggleUserClock(int userId) {
        Clock clock = getUserClock(userId);

        if (clock.getUserId() == null) {
            createUserClock(userId);
            return clock;
        }

        clock.setStatus(!clock.getStatus());

        if (!clock.getStatus())
            createWorkingTime(userId, clock.getTime());

        clockRepository.updateClock(clock, userId);

        return clock;
    }

    private Clock createUserClock(int userId) {
        Clock clock = new Clock();

        clock.setUserId(userId);
        clock.setTime(new Timestamp(new Date().getTime()));
        clock.setStatus(true);

        clockRepository.createClock(clock, userId);

        return clock;
    }

    private void createWorkingTime(int userId, Timestamp start) {
        WorkingTime workingTime = new WorkingTime();

        workingTime.setStart(start);
        workingTime.setEnd(new Timestamp(new Date().getTime()));

        workingTimeRepository.createWorkingTime(workingTime, userId);
    }
}
