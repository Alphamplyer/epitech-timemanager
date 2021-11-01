package edu.epitech.timemanager.domains.usescases.working_times.queries.get_working_time;

import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.shared.cqrs.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetWorkingTimeQueryResult implements QueryResult {
    private WorkingTime workingTime;
}
