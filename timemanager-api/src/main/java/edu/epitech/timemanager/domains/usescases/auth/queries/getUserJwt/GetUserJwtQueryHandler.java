package edu.epitech.timemanager.domains.usescases.auth.queries.getUserJwt;

import edu.epitech.timemanager.domains.services.JwtService;
import edu.epitech.timemanager.shared.cqrs.QueryHandler;
import edu.epitech.timemanager.shared.exceptions.ApiErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserJwtQueryHandler implements QueryHandler<GetUserJwtQuery, GetUserJwtQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserJwtQueryHandler.class);

    private final JwtService jwtService;

    @Autowired
    public GetUserJwtQueryHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public GetUserJwtQueryResult execute(GetUserJwtQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetUserJwtQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);



        String token = jwtService.generateToken(query.getUser(), query.getRememberMe());

        return new GetUserJwtQueryResult(token);
    }
}
