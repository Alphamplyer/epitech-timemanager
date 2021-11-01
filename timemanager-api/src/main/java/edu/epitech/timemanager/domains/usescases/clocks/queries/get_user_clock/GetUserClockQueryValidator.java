package edu.epitech.timemanager.domains.usescases.clocks.queries.get_user_clock;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class GetUserClockQueryValidator {
    public static boolean isValid(GetUserClockQuery getUserClockQuery, List<String> errors) {
        return IdValidator.isValid(getUserClockQuery.getUserId(), errors);
    }
}
