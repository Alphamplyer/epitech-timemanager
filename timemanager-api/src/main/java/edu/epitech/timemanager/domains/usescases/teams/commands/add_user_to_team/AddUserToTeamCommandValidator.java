package edu.epitech.timemanager.domains.usescases.teams.commands.add_user_to_team;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class AddUserToTeamCommandValidator {
    public static boolean isValid(AddUserToTeamCommand command, List<String> errors) {
        return IdValidator.isValid(command.getUserId(), errors) && IdValidator.isValid(command.getTeamId(), errors);
    }
}
