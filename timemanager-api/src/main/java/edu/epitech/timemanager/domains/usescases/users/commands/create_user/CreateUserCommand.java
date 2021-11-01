package edu.epitech.timemanager.domains.usescases.users.commands.create_user;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserCommand implements Command {
    private String username;
    private String email;
    private String password;
}
