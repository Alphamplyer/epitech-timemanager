package edu.epitech.timemanager.domains.usescases.clocks.commands.toggle_user_clock;

import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.usescases.clocks.commands.create_clock.CreateClockCommand;
import edu.epitech.timemanager.domains.usescases.clocks.commands.create_clock.CreateClockCommandHandler;
import edu.epitech.timemanager.domains.usescases.working_times.commands.create_working_time.CreateWorkingTimeCommand;
import edu.epitech.timemanager.domains.usescases.working_times.commands.create_working_time.CreateWorkingTimeCommandHandler;
import edu.epitech.timemanager.persistence.ClockRepository;
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
public class ToggleUserClockCommandHandler implements CommandHandler<ToggleUserClockCommand> {

    private static final Logger logger = LoggerFactory.getLogger(ToggleUserClockCommandHandler.class);

    private final ClockRepository clockRepository;
    private final CreateClockCommandHandler createClockCommand;
    private final CreateWorkingTimeCommandHandler createWorkingTimeCommand;

    @Autowired
    public ToggleUserClockCommandHandler(
        ClockRepository clockRepository,
        CreateClockCommandHandler createClockCommand,
        CreateWorkingTimeCommandHandler createWorkingTimeCommand
    ) {
        this.clockRepository = clockRepository;
        this.createClockCommand = createClockCommand;
        this.createWorkingTimeCommand = createWorkingTimeCommand;
    }

    @Override
    public void execute(ToggleUserClockCommand command) {
        List<String> errors = new ArrayList<>();
        if (!ToggleUserClockCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        Optional<Clock> existingClock;

        try {
            existingClock = clockRepository.findClockByUser(new User(command.getUserId()));

            if (existingClock.isPresent()) {
                Clock clock = existingClock.get();
                clock.setEnable(!clock.isEnable());

                if (!clock.isEnable()) {
                    createWorkingTimeCommand.execute(
                        new CreateWorkingTimeCommand(
                            command.getUserId(),
                            clock.getEnabledAt(),
                            new Date(System.currentTimeMillis())
                        )
                    );
                }

                clockRepository.save(clock);
            } else {
                createClockCommand.execute(new CreateClockCommand(command.getUserId()));
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
