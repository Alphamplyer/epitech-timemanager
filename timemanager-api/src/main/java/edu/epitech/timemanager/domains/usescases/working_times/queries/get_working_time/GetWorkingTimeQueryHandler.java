package edu.epitech.timemanager.domains.usescases.working_times.queries.get_working_time;

import edu.epitech.timemanager.domains.models.WorkingTime;
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
import java.util.Optional;

@Service
public class GetWorkingTimeQueryHandler implements QueryHandler<GetWorkingTimeQuery, GetWorkingTimeQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetWorkingTimeQueryHandler.class);

    private final WorkingTimeRepository workingTimeRepository;

    @Autowired
    public GetWorkingTimeQueryHandler(WorkingTimeRepository workingTimeRepository) {
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public GetWorkingTimeQueryResult execute(GetWorkingTimeQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetWorkingTimeQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        WorkingTime workingTime;
        GetWorkingTimeQueryResult result = new GetWorkingTimeQueryResult();

        try {
            Optional<WorkingTime> existingWorkingTime = workingTimeRepository.findById(query.getId());

            if (existingWorkingTime.isPresent()) {
                workingTime = existingWorkingTime.get();
            } else {
                throw new ApiErrorException("Working Time not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        result.setWorkingTime(workingTime);
        return result;
    }
}
