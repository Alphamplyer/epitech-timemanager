package edu.epitech.timemanager.domains.usescases.working_times.commands.delete_working_time;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class DeleteWorkingTimeCommandValidator {
    public static boolean isValid(DeleteWorkingTimeCommand deleteWorkingTimeCommand, List<String> errors) {
        return IdValidator.isValid(deleteWorkingTimeCommand.getId(), errors);
    }
}
