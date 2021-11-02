package edu.epitech.timemanager.domains.usescases.teams.commands.remove_user_from_team;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RemoveUserFromTeamCommand implements Command {
    private Integer userId;
    private Integer teamId;
}
