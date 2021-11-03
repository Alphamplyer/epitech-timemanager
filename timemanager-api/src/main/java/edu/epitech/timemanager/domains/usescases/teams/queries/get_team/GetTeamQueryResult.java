package edu.epitech.timemanager.domains.usescases.teams.queries.get_team;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.shared.cqrs.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTeamQueryResult implements QueryResult {
    private Team team;
}
