package edu.epitech.timemanager.domains.usescases.teams.queries.get_user_teams;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.persistence.TeamRepository;
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

@Service
public class GetUserTeamsQueryHandler implements QueryHandler<GetUserTeamsQuery, GetUserTeamsQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserTeamsQueryHandler.class);

    private final TeamRepository teamRepository;

    @Autowired
    public GetUserTeamsQueryHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public GetUserTeamsQueryResult execute(GetUserTeamsQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetUserTeamsQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        List<Team> teams;
        GetUserTeamsQueryResult result = new GetUserTeamsQueryResult();

        try {
            teams = teamRepository.findUserTeams(query.getUserId());
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        result.setTeams(teams);
        return result;
    }
}
