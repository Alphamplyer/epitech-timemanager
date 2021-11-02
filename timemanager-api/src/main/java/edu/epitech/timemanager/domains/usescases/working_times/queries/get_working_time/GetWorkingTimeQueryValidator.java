package edu.epitech.timemanager.domains.usescases.working_times.queries.get_working_time;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class GetWorkingTimeQueryValidator {
    public static boolean isValid(GetWorkingTimeQuery getWorkingTimeQuery, List<String> errors) {
        return IdValidator.isValid(getWorkingTimeQuery.getId(), errors);
    }
}
