package edu.epitech.timemanager.domains.usescases.teams.commands.delete_team;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteTeamCommand implements Command {
    private Integer id;
}
