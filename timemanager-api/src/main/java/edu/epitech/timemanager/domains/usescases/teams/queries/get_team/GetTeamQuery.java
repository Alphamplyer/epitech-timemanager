package edu.epitech.timemanager.domains.usescases.teams.queries.get_team;

import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTeamQuery implements Query {
    private Integer id;
}
