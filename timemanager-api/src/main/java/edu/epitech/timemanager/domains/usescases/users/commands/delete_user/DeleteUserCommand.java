package edu.epitech.timemanager.domains.usescases.users.commands.delete_user;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserCommand implements Command {
    private Integer id;
}
