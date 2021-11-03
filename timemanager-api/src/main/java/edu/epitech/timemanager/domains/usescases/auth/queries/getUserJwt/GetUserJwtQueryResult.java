package edu.epitech.timemanager.domains.usescases.auth.queries.getUserJwt;

import edu.epitech.timemanager.shared.cqrs.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserJwtQueryResult implements QueryResult {
    private String token;
}
