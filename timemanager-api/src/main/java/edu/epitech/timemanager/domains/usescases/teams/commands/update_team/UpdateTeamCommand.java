package edu.epitech.timemanager.domains.usescases.teams.commands.update_team;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTeamCommand implements Command {
    private Integer id;
    private String name;
    private String description;
}
