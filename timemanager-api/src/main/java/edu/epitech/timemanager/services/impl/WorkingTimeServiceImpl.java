package edu.epitech.timemanager.services.impl;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.persistence.WorkingTimeRepository;
import edu.epitech.timemanager.services.WorkingTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class WorkingTimeServiceImpl implements WorkingTimeService {
    private final WorkingTimeRepository workingTimeRepository;

    @Override
    public WorkingTime getWorkingTime(int id) {
        return workingTimeRepository.findById(id).orElse(null);
    }

    @Override
    public List<WorkingTime> getWorkingTimes(int userId) {
        return workingTimeRepository.findWorkingTimesByUser_Id(userId);
    }

    @Override
    public List<WorkingTime> getWorkingTimes(int userId, Date startInterval, Date endInterval) {
        return workingTimeRepository.findWorkingTimesByUserAndStartedAtAfterAndEndedAtBefore(
            new User(userId),
            startInterval,
            endInterval
        );
    }

    @Override
    public WorkingTime createWorkingTime(WorkingTime workingTime) {
        return workingTimeRepository.save(workingTime);
    }

    @Override
    public WorkingTime updateWorkingTime(int modifierId, WorkingTime workingTime) {
        WorkingTime persistedWorkingTime = workingTimeRepository.findById(workingTime.getId()).orElse(null);

        if (persistedWorkingTime == null) {
            return null;
        }

        if (persistedWorkingTime.getUser().getId() != modifierId)
            throw new RuntimeException("UNAUTHORIZED");

        persistedWorkingTime.setCreatedAt(workingTime.getCreatedAt());
        persistedWorkingTime.setEndedAt(workingTime.getEndedAt());

        return workingTimeRepository.save(persistedWorkingTime);
    }

    @Override
    public void deleteWorkingTime(int id, int deleterId) {
        WorkingTime workingTime = workingTimeRepository.findById(id).orElse(null);

        if (workingTime != null && workingTime.getUser().getId() != deleterId) {
            throw new RuntimeException("UNAUTHORIZED");
        }

        workingTimeRepository.deleteById(id);
    }
}
