package edu.epitech.timemanager.domains.usescases.users.queries.get_all_users;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.shared.cqrs.QueryHandler;
import edu.epitech.timemanager.shared.exceptions.ApiErrorException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllUsersQueryHandler implements QueryHandler<GetAllUsersQuery, GetAllUsersQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetAllUsersQueryHandler.class);

    private final UserRepository userRepository;

    @Autowired
    public GetAllUsersQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetAllUsersQueryResult execute(GetAllUsersQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetAllUsersQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        List<User> users;
        GetAllUsersQueryResult result = new GetAllUsersQueryResult();

        try {
            users = userRepository.findAll(Sort.by("username"));
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        result.setUsers(users);
        return result;
    }
}
