package edu.epitech.timemanager.domains.usescases.teams.queries.get_teams;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.shared.cqrs.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTeamsQueryResult implements QueryResult {
    private List<Team> teams;
}
