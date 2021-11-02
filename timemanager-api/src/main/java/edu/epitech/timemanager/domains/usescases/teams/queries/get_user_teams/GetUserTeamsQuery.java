package edu.epitech.timemanager.domains.usescases.teams.queries.get_user_teams;

import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserTeamsQuery implements Query {
    private Integer userId;
}
