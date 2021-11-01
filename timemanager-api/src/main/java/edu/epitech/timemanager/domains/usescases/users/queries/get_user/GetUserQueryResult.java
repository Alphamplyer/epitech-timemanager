package edu.epitech.timemanager.domains.usescases.users.queries.get_user;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.shared.cqrs.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserQueryResult implements QueryResult {
    private User user;
}
