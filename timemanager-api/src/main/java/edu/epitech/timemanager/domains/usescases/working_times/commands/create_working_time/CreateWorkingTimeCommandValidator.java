package edu.epitech.timemanager.domains.usescases.working_times.commands.create_working_time;

import edu.epitech.timemanager.shared.validators.EmailValidator;
import edu.epitech.timemanager.shared.validators.IdValidator;
import edu.epitech.timemanager.shared.validators.UsernameValidator;

import java.util.List;

public class CreateWorkingTimeCommandValidator {
    public static boolean isValid(CreateWorkingTimeCommand createWorkingTimeCommand, List<String> errors) {
        return IdValidator.isValid(createWorkingTimeCommand.getUserId(), errors)
            && createWorkingTimeCommand.getStartedAt().before(createWorkingTimeCommand.getEndedAt());
    }
}
