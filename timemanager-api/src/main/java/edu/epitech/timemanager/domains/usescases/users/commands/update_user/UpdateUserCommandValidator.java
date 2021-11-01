package edu.epitech.timemanager.domains.usescases.users.commands.update_user;

import edu.epitech.timemanager.shared.validators.EmailValidator;
import edu.epitech.timemanager.shared.validators.IdValidator;
import edu.epitech.timemanager.shared.validators.UsernameValidator;

import java.util.List;

public class UpdateUserCommandValidator {
    public static boolean isValid(UpdateUserCommand updateUserCommand, List<String> errors) {
        return IdValidator.isValid(updateUserCommand.getId(), errors)
            && EmailValidator.isValid(updateUserCommand.getEmail(), errors)
            && UsernameValidator.isValid(updateUserCommand.getUsername(), errors);
    }
}