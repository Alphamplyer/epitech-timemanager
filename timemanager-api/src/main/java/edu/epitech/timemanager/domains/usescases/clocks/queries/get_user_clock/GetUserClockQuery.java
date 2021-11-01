package edu.epitech.timemanager.domains.usescases.clocks.queries.get_user_clock;

import edu.epitech.timemanager.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserClockQuery implements Query {
    private Integer userId;
}
