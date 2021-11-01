package edu.epitech.timemanager.domains.usescases.users.commands.create_user;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.shared.cqrs.CommandHandler;
import edu.epitech.timemanager.shared.exceptions.ApiErrorException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserCommandHandler.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(CreateUserCommand command) {
        List<String> errors = new ArrayList<>();
        if (!CreateUserCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        User user = new User();
        user.setUsername(command.getUsername());
        user.setEmail(command.getEmail());
        user.setHashedPassword(passwordEncoder.encode(command.getPassword()));
        user.setRole(Role.EMPLOYEE);

        try {
            userRepository.save(user);
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
