package edu.epitech.timemanager.domains.usescases.working_times.queries.get_user_working_times;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.persistence.WorkingTimeRepository;
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

@Service
public class GetUserWorkingTimesQueryHandler implements QueryHandler<GetUserWorkingTimesQuery, GetUserWorkingTimesQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserWorkingTimesQueryHandler.class);

    private final WorkingTimeRepository workingTimeRepository;
    private final UserRepository userRepository;

    @Autowired
    public GetUserWorkingTimesQueryHandler(WorkingTimeRepository workingTimeRepository, UserRepository userRepository) {
        this.workingTimeRepository = workingTimeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GetUserWorkingTimesQueryResult execute(GetUserWorkingTimesQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetUserWorkingTimesQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        List<WorkingTime> workingTimes;
        GetUserWorkingTimesQueryResult result = new GetUserWorkingTimesQueryResult();

        try {
            if (userRepository.existsById(query.getUserId())) {
                workingTimes = workingTimeRepository.findWorkingTimesByUserAndStartedAtAfterAndEndedAtBefore(
                    new User(query.getUserId()),
                    query.getStart(),
                    query.getEnd()
                );
            } else {
                throw new ApiErrorException("User not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        result.setWorkingTimes(workingTimes);
        return result;
    }
}
