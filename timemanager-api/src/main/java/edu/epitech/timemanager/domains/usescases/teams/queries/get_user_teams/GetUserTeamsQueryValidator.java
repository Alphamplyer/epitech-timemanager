package edu.epitech.timemanager.domains.usescases.teams.queries.get_user_teams;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class GetUserTeamsQueryValidator {
    public static boolean isValid(GetUserTeamsQuery getUserTeamsQuery, List<String> errors) {
        return IdValidator.isValid(getUserTeamsQuery.getUserId(), errors);
    }
}
