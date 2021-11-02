package edu.epitech.timemanager.domains.usescases.users.queries.get_user;

import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GetUserQuery implements Query {
    private Integer id;
}
