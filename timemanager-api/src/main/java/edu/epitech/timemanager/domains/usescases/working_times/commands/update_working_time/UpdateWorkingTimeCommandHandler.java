package edu.epitech.timemanager.domains.usescases.working_times.commands.update_working_time;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.persistence.UserRepository;
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
import java.util.Optional;

@Service
public class UpdateWorkingTimeCommandHandler implements CommandHandler<UpdateWorkingTimeCommand> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateWorkingTimeCommandHandler.class);

    private final WorkingTimeRepository workingTimeRepository;

    @Autowired
    public UpdateWorkingTimeCommandHandler(WorkingTimeRepository workingTimeRepository) {
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public void execute(UpdateWorkingTimeCommand command) {
        List<String> errors = new ArrayList<>();
        if (!UpdateWorkingTimeCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            Optional<WorkingTime> existingWorkingTime = workingTimeRepository.findById(command.getId());

            if (existingWorkingTime.isPresent()) {
                WorkingTime workingTime = existingWorkingTime.get();
                workingTime.setStartedAt(command.getStartedAt());
                workingTime.setEndedAt(command.getEndedAt());

                workingTimeRepository.save(workingTime);
            } else {
                throw new ApiErrorException("Working Time not found", HttpStatus.NOT_FOUND, null);
            }
        }
        catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
