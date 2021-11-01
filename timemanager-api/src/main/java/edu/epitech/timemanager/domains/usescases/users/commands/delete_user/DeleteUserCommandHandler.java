package edu.epitech.timemanager.domains.usescases.users.commands.delete_user;

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

@Service
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserCommandHandler.class);

    private final UserRepository userRepository;

    @Autowired
    public DeleteUserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DeleteUserCommand command) {
        List<String> errors = new ArrayList<>();
        if (!DeleteUserCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            if (userRepository.existsById(command.getId())) {
                userRepository.deleteById(command.getId());
            } else {
                throw new ApiErrorException("User not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
