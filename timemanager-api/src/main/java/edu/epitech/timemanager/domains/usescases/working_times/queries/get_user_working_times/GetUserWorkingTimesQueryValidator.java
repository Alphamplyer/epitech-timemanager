package edu.epitech.timemanager.domains.usescases.working_times.queries.get_user_working_times;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class GetUserWorkingTimesQueryValidator {
    public static boolean isValid(GetUserWorkingTimesQuery getUserWorkingTimesQuery, List<String> errors) {
        return IdValidator.isValid(getUserWorkingTimesQuery.getUserId(), errors)
            && getUserWorkingTimesQuery.getStart().before(getUserWorkingTimesQuery.getEnd());
    }
}
