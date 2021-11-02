package edu.epitech.timemanager.domains.usescases.users.commands.demote_manager_to_employee;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DemoteManagerToEmployeeCommand implements Command {
    private Integer userId;
}
