package edu.epitech.timemanager.domains.usescases.teams.commands.update_team;

import edu.epitech.timemanager.shared.validators.IdValidator;
import edu.epitech.timemanager.shared.validators.NameValidator;

import java.util.List;

public class UpdateTeamCommandValidator {
    public static boolean isValid(UpdateTeamCommand updateTeamCommand, List<String> errors) {
        return IdValidator.isValid(updateTeamCommand.getId(), errors)
            && NameValidator.isValid(updateTeamCommand.getName(), errors);
    }
}