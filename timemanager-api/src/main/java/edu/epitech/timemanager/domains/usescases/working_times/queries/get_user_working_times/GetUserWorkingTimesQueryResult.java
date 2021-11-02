package edu.epitech.timemanager.domains.usescases.working_times.queries.get_user_working_times;

import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.shared.cqrs.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserWorkingTimesQueryResult implements QueryResult {
    List<WorkingTime> workingTimes;
}
