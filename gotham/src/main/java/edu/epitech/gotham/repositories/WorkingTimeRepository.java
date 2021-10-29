package edu.epitech.gotham.repositories;

import edu.epitech.gotham.models.WorkingTime;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface WorkingTimeRepository {
    List<WorkingTime> getUserWorkingTimes(int userId, Timestamp start, Timestamp end);
    Optional<WorkingTime> getWorkingTimeById(int id);

    int createWorkingTime(WorkingTime workingTime, int userId);
    int updateWorkingTime(WorkingTime workingTime, int id);
    int deleteWorkingTime(int id);
}
