package edu.epitech.timemanager.domains.usescases.working_times.commands.create_working_time;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.persistence.WorkingTimeRepository;
import edu.epitech.timemanager.shared.cqrs.CommandHandler;
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
public class CreateWorkingTimeCommandHandler implements CommandHandler<CreateWorkingTimeCommand> {

    private static final Logger logger = LoggerFactory.getLogger(CreateWorkingTimeCommandHandler.class);

    private final WorkingTimeRepository workingTimeRepository;

    @Autowired
    public CreateWorkingTimeCommandHandler(WorkingTimeRepository workingTimeRepository) {
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public void execute(CreateWorkingTimeCommand command) {
        List<String> errors = new ArrayList<>();
        if (!CreateWorkingTimeCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        WorkingTime workingTime = new WorkingTime();
        workingTime.setStartedAt(command.getStartedAt());
        workingTime.setEndedAt(command.getEndedAt());
        workingTime.setUser(new User(command.getUserId()));

        try {
            workingTimeRepository.save(workingTime);
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
