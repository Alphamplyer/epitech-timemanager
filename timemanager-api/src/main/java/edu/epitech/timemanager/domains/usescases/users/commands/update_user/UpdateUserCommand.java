package edu.epitech.timemanager.domains.usescases.users.commands.update_user;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserCommand implements Command {
    private Integer id;
    private String username;
    private String email;
}
