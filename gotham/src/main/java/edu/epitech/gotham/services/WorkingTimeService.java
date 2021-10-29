package edu.epitech.gotham.services;

import edu.epitech.gotham.models.WorkingTime;

import java.sql.Timestamp;
import java.util.List;

public interface WorkingTimeService {
    List<WorkingTime> getUserWorkingTimes(int userId, Timestamp start, Timestamp end);
    WorkingTime getWorkingTimeById(int id);

    void createWorkingTime(WorkingTime workingTime, int userId);
    void updateWorkingTime(WorkingTime workingTime, int id);
    void deleteWorkingTime(int id);
}
