package edu.epitech.timemanager.services;

import edu.epitech.timemanager.domains.models.WorkingTime;

import java.util.Date;
import java.util.List;

public interface WorkingTimeService {
    WorkingTime getWorkingTime(int id);
    List<WorkingTime> getWorkingTimes(int userId);
    List<WorkingTime> getWorkingTimes(int userId, Date startInterval, Date endInterval);

    WorkingTime createWorkingTime(WorkingTime workingTime);
    WorkingTime updateWorkingTime(int id, WorkingTime workingTime);
    void deleteWorkingTime(int id, int deleterId);
}
