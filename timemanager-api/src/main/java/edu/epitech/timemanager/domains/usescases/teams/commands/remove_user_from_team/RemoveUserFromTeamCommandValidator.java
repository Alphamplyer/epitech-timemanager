package edu.epitech.timemanager.domains.usescases.teams.commands.remove_user_from_team;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class RemoveUserFromTeamCommandValidator {
    public static boolean isValid(RemoveUserFromTeamCommand command, List<String> errors) {
        return IdValidator.isValid(command.getUserId(), errors) && IdValidator.isValid(command.getTeamId(), errors);
    }
}
