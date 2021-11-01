package edu.epitech.timemanager.domains.usescases.working_times.commands.update_working_time;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class UpdateWorkingTimeCommandValidator {
    public static boolean isValid(UpdateWorkingTimeCommand updateWorkingTimeCommand, List<String> errors) {
        return IdValidator.isValid(updateWorkingTimeCommand.getId(), errors)
            && updateWorkingTimeCommand.getStartedAt().before(updateWorkingTimeCommand.getEndedAt());
    }
}