package edu.epitech.timemanager.domains.usescases.clocks.commands.toggle_user_clock;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class ToggleUserClockCommandValidator {
    public static boolean isValid(ToggleUserClockCommand toggleUserClockCommand, List<String> errors) {
        return IdValidator.isValid(toggleUserClockCommand.getUserId(), errors);
    }
}
