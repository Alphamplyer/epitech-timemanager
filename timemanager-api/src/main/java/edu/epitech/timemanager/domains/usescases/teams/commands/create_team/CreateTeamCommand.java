package edu.epitech.timemanager.domains.usescases.teams.commands.create_team;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTeamCommand implements Command {
    public Integer userId;
    public String name;
    public String description;
}
