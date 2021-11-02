package edu.epitech.timemanager.domains.usescases.users.commands.promote_employee_to_manager;

import edu.epitech.timemanager.shared.validators.EmailValidator;
import edu.epitech.timemanager.shared.validators.IdValidator;
import edu.epitech.timemanager.shared.validators.UsernameValidator;

import java.util.List;

public class PromoteEmployeeToManagerCommandValidator {
    public static boolean isValid(PromoteEmployeeToManagerCommand promoteEmployeeToManagerCommand, List<String> errors) {
        return IdValidator.isValid(promoteEmployeeToManagerCommand.getUserId(), errors);
    }
}