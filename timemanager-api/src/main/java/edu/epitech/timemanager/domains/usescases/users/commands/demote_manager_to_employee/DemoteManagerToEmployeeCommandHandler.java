package edu.epitech.timemanager.domains.usescases.users.commands.demote_manager_to_employee;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemoteManagerToEmployeeCommandHandler implements CommandHandler<DemoteManagerToEmployeeCommand> {

    private static final Logger logger = LoggerFactory.getLogger(DemoteManagerToEmployeeCommandHandler.class);

    private final UserRepository userRepository;

    @Autowired
    public DemoteManagerToEmployeeCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(DemoteManagerToEmployeeCommand command) {
        List<String> errors = new ArrayList<>();
        if (!DemoteManagerToEmployeeCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            Optional<User> existingUser = userRepository.findById(command.getUserId());

            if (existingUser.isPresent()) {
                User user = existingUser.get();
                user.setRole(Role.EMPLOYEE);
                
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
