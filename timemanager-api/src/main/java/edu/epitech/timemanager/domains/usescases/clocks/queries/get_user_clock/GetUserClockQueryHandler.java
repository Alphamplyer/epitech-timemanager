package edu.epitech.timemanager.domains.usescases.clocks.queries.get_user_clock;

import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.persistence.ClockRepository;
import edu.epitech.timemanager.shared.cqrs.QueryHandler;
import edu.epitech.timemanager.shared.exceptions.ApiErrorException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetUserClockQueryHandler implements QueryHandler<GetUserClockQuery, GetUserClockQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserClockQueryHandler.class);

    private final ClockRepository clockRepository;

    @Autowired
    public GetUserClockQueryHandler(ClockRepository clockRepository) {
        this.clockRepository = clockRepository;
    }

    @Override
    public GetUserClockQueryResult execute(GetUserClockQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetUserClockQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        Clock clock;
        GetUserClockQueryResult result = new GetUserClockQueryResult();

        try {
            Optional<Clock> existingClock = clockRepository.findClockByUser(new User(query.getUserId()));

            if (existingClock.isPresent()) {
                clock = existingClock.get();
            } else {
                throw new ApiErrorException("Clock not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        result.setClock(clock);
        return result;
    }
}
