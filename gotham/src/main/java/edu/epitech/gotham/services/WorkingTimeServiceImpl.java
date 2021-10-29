package edu.epitech.gotham.services;

import edu.epitech.gotham.models.WorkingTime;
import edu.epitech.gotham.repositories.WorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class WorkingTimeServiceImpl implements WorkingTimeService {

    private final WorkingTimeRepository workingTimeRepository;

    @Autowired
    public WorkingTimeServiceImpl(WorkingTimeRepository workingTimeRepository) {
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public List<WorkingTime> getUserWorkingTimes(int userId, Timestamp start, Timestamp end) {
        return workingTimeRepository.getUserWorkingTimes(userId, start, end);
    }

    @Override
    public WorkingTime getWorkingTimeById(int id) {
        WorkingTime workingTime = new WorkingTime();
        Optional<WorkingTime> existingWorkingTime = workingTimeRepository.getWorkingTimeById(id);
        if (existingWorkingTime.isPresent())
            workingTime = existingWorkingTime.get();
        return workingTime;
    }

    @Override
    public void createWorkingTime(WorkingTime workingTime, int userId) {
        workingTimeRepository.createWorkingTime(workingTime, userId);
    }

    @Override
    public void updateWorkingTime(WorkingTime workingTime, int id) {
        workingTimeRepository.updateWorkingTime(workingTime, id);
    }

    @Override
    public void deleteWorkingTime(int id) {
        workingTimeRepository.deleteWorkingTime(id);
    }
}
