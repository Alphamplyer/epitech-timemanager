package edu.epitech.timemanager.domains.usescases.auth.queries.getUserJwt;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserJwtQuery implements Query {
    private User user;
    private Boolean rememberMe;
}
