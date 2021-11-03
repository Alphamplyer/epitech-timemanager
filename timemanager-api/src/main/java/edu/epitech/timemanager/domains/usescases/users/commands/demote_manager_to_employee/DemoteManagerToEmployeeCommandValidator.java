package edu.epitech.timemanager.domains.usescases.users.commands.demote_manager_to_employee;

import edu.epitech.timemanager.shared.validators.IdValidator;

import java.util.List;

public class DemoteManagerToEmployeeCommandValidator {
    public static boolean isValid(DemoteManagerToEmployeeCommand demoteManagerToEmployeeCommand, List<String> errors) {
        return IdValidator.isValid(demoteManagerToEmployeeCommand.getUserId(), errors);
    }
}