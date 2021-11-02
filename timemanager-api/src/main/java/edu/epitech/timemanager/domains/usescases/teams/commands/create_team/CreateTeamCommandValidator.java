package edu.epitech.timemanager.domains.usescases.teams.commands.create_team;

import edu.epitech.timemanager.shared.validators.IdValidator;
import edu.epitech.timemanager.shared.validators.NameValidator;

import java.util.List;

public class CreateTeamCommandValidator {
    public static boolean isValid(CreateTeamCommand createTeamCommand, List<String> errors) {
        return IdValidator.isValid(createTeamCommand.getUserId(), errors)
                && NameValidator.isValid(createTeamCommand.getName(), errors);
    }
}
