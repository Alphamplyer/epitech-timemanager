package edu.epitech.timemanager.domains.usescases.teams.queries.get_team;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class GetTeamQueryValidator {
    public static boolean isValid(GetTeamQuery getTeamQuery, List<String> errors) {
        return IdValidator.isValid(getTeamQuery.getId(), errors);
    }
}
