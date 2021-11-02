package edu.epitech.timemanager.domains.usescases.auth.queries.getUserJwt;

import edu.epitech.timemanager.configurations.security.CustomUserDetails;
import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserJwtQuery implements Query {
    private CustomUserDetails user;
    private Boolean rememberMe;
}
