package edu.epitech.timemanager.domains.usescases.teams.commands.add_user_to_team;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddUserToTeamCommand implements Command {
    private Integer userId;
    private Integer teamId;
}
