package edu.epitech.timemanager.domains.usescases.working_times.queries.get_working_time;

import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetWorkingTimeQuery implements Query {
    private Integer id;
}
