package edu.epitech.timemanager.domains.usescases.users.commands.delete_user;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class DeleteUserCommandValidator {
    public static boolean isValid(DeleteUserCommand deleteUserCommand, List<String> errors) {
        return IdValidator.isValid(deleteUserCommand.getId(), errors);
    }
}
