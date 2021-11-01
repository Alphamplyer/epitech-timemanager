package edu.epitech.timemanager.domains.usescases.users.queries.get_user;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.persistence.UserRepository;
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
public class GetUserQueryHandler implements QueryHandler<GetUserQuery, GetUserQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserQueryHandler.class);

    private final UserRepository userRepository;

    @Autowired
    public GetUserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserQueryResult execute(GetUserQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetUserQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        User user;
        GetUserQueryResult result = new GetUserQueryResult();

        try {
            Optional<User> existingUser = userRepository.findById(query.getId());

            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                throw new ApiErrorException("User not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        result.setUser(user);
        return result;
    }
}
