package edu.epitech.timemanager.domains.usescases.users.queries.get_all_users;

import edu.epitech.timemanager.domains.models.User;
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
public class GetAllUsersQueryResult implements QueryResult {
    List<User> users;
}
