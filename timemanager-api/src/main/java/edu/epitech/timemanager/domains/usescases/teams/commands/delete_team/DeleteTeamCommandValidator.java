package edu.epitech.timemanager.domains.usescases.teams.commands.delete_team;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class DeleteTeamCommandValidator {
    public static boolean isValid(DeleteTeamCommand deleteTeamCommand, List<String> errors) {
        return IdValidator.isValid(deleteTeamCommand.getId(), errors);
    }
}
