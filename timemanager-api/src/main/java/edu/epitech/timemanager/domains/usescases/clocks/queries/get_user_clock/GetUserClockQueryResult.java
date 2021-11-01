package edu.epitech.timemanager.domains.usescases.clocks.queries.get_user_clock;

import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.shared.cqrs.QueryResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserClockQueryResult implements QueryResult {
    private Clock clock;
}
