package edu.epitech.timemanager.domains.usescases.clocks.commands.create_clock;

import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.persistence.ClockRepository;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.shared.cqrs.CommandHandler;
import edu.epitech.timemanager.shared.exceptions.ApiErrorException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CreateClockCommandHandler implements CommandHandler<CreateClockCommand> {

    private static final Logger logger = LoggerFactory.getLogger(CreateClockCommandHandler.class);

    private final ClockRepository clockRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreateClockCommandHandler(ClockRepository clockRepository, UserRepository userRepository) {
        this.clockRepository = clockRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(CreateClockCommand command) {
        List<String> errors = new ArrayList<>();
        if (!CreateClockCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            Optional<User> existingUser = userRepository.findById(command.getUserId());

            if (existingUser.isPresent()) {
                Clock clock = new Clock();
                clock.setEnable(true);
                clock.setEnabledAt(new Date(System.currentTimeMillis()));
                clock.setUser(existingUser.get());

                clockRepository.save(clock);
            } else {
                throw new ApiErrorException("User not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
