package edu.epitech.timemanager.domains.usescases.users.commands.promote_employee_to_manager;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PromoteEmployeeToManagerCommand implements Command {
    private Integer userId;
}
