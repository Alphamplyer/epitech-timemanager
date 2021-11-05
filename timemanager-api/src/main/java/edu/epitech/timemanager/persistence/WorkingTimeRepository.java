package edu.epitech.timemanager.persistence;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Integer> {
    List<WorkingTime> findWorkingTimesByUser_Id(int userId);
    List<WorkingTime> findWorkingTimesByUserAndStartedAtAfterAndEndedAtBefore(User user, Date startedAt, Date endedAt);
}
