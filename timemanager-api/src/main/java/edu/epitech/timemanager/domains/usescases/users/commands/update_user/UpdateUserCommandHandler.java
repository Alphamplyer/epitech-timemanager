package edu.epitech.timemanager.domains.usescases.users.commands.update_user;

import edu.epitech.timemanager.domains.models.User;
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
import java.util.List;
import java.util.Optional;

@Service
public class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserCommandHandler.class);

    private final UserRepository userRepository;

    @Autowired
    public UpdateUserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UpdateUserCommand command) {
        List<String> errors = new ArrayList<>();
        if (!UpdateUserCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            if (userRepository.existsUserByUsername(command.getUsername()))
                throw new ApiErrorException("Username already exists", HttpStatus.CONFLICT, null);
            if (userRepository.existsUserByEmail(command.getEmail()))
                throw new ApiErrorException("Email already exists", HttpStatus.CONFLICT, null);

            Optional<User> existingUser = userRepository.findById(command.getId());

            if (existingUser.isPresent()) {
                User user = existingUser.get();
                user.setUsername(command.getUsername());
                user.setEmail(command.getEmail());

                userRepository.save(user);
            } else {
                throw new ApiErrorException("User not found", HttpStatus.NOT_FOUND, null);
            }
        }
        catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
