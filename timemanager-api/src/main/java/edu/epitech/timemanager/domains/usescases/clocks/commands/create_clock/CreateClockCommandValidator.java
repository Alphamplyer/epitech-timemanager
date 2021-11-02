package edu.epitech.timemanager.domains.usescases.clocks.commands.create_clock;

import edu.epitech.timemanager.shared.validators.EmailValidator;
import edu.epitech.timemanager.shared.validators.IdValidator;
import edu.epitech.timemanager.shared.validators.UsernameValidator;

import java.util.List;

public class CreateClockCommandValidator {
    public static boolean isValid(CreateClockCommand createClockCommand, List<String> errors) {
        return IdValidator.isValid(createClockCommand.getUserId(), errors);
    }
}
