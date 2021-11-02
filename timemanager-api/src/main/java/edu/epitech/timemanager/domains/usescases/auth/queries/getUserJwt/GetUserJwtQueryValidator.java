package edu.epitech.timemanager.domains.usescases.auth.queries.getUserJwt;

import edu.epitech.timemanager.shared.validators.EmailValidator;
import edu.epitech.timemanager.shared.validators.IdValidator;
import edu.epitech.timemanager.shared.validators.NameValidator;

import java.util.List;

public class GetUserJwtQueryValidator {
    public static boolean isValid(GetUserJwtQuery query, List<String> errors) {
        return IdValidator.isValid(query.getUser().getId(), errors)
            && NameValidator.isValid(query.getUser().getUsername(), errors)
            && EmailValidator.isValid(query.getUser().getEmail(), errors);
    }
}
