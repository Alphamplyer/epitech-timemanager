package edu.epitech.timemanager.services.impl;

import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.persistence.ClockRepository;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.persistence.WorkingTimeRepository;
import edu.epitech.timemanager.services.ClockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ClockServiceImpl implements ClockService {
    private final ClockRepository clockRepository;
    private final UserRepository userRepository;
    private final WorkingTimeRepository workingTimeRepository;

    @Override
    public Clock getUserClock(int userId) {
        return clockRepository.findClockByUser_Id(userId).orElse(null);
    }

    @Override
    public Clock toggleUserClock(int userId) {
        Clock clock = getUserClock(userId);

        if (clock == null && userRepository.existsById(userId)) {
             return clockRepository.save(
                new Clock(
                    new Date(System.currentTimeMillis()),
                    true,
                    new User(userId)
                )
            );
        } else if (clock == null) {
            return null;
        }

        if (clock.isEnable()) {
            workingTimeRepository.save(
                new WorkingTime(
                    clock.getEnabledAt(),
                    new Date(System.currentTimeMillis()),
                    new User(userId)
                )
            );
        } else {
            clock.setEnabledAt(new Date(System.currentTimeMillis()));
        }

        clock.setEnable(!clock.isEnable());
        return clockRepository.save(clock);
    }
}
