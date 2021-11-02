package edu.epitech.timemanager.domains.usescases.users.commands.create_user;

import edu.epitech.timemanager.shared.validators.EmailValidator;
import edu.epitech.timemanager.shared.validators.UsernameValidator;

import java.util.List;

public class CreateUserCommandValidator {
    public static boolean isValid(CreateUserCommand createUserCommand, List<String> errors) {
        return EmailValidator.isValid(createUserCommand.getEmail(), errors)
            && UsernameValidator.isValid(createUserCommand.getUsername(), errors);
    }
}
