package edu.epitech.timemanager.domains.usescases.users.queries.get_user;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class GetUserQueryValidator {
    public static boolean isValid(GetUserQuery getUserQuery, List<String> errors) {
        return IdValidator.isValid(getUserQuery.getId(), errors);
    }
}
