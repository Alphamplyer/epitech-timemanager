package edu.epitech.timemanager.domains.usescases.working_times.queries.get_user_working_times;

import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class GetUserWorkingTimesQuery implements Query {
    private Integer userId;
    private Date start;
    private Date end;
}
