package edu.epitech.timemanager.domains.usescases.working_times.commands.delete_working_time;

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
public class DeleteWorkingTimeCommandHandler implements CommandHandler<DeleteWorkingTimeCommand> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteWorkingTimeCommandHandler.class);

    private final WorkingTimeRepository workingTimeRepository;

    @Autowired
    public DeleteWorkingTimeCommandHandler(WorkingTimeRepository workingTimeRepository) {
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public void execute(DeleteWorkingTimeCommand command) {
        List<String> errors = new ArrayList<>();
        if (!DeleteWorkingTimeCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            if (workingTimeRepository.existsById(command.getId())) {
                workingTimeRepository.deleteById(command.getId());
            } else {
                throw new ApiErrorException("Working Time not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
