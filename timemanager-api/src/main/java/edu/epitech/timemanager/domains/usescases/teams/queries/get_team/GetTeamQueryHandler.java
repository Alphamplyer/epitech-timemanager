package edu.epitech.timemanager.domains.usescases.teams.queries.get_team;

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
import java.util.Optional;

@Service
public class GetTeamQueryHandler implements QueryHandler<GetTeamQuery, GetTeamQueryResult> {

    private static final Logger logger = LoggerFactory.getLogger(GetTeamQueryHandler.class);

    private final TeamRepository teamRepository;

    @Autowired
    public GetTeamQueryHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public GetTeamQueryResult execute(GetTeamQuery query) {
        List<String> errors = new ArrayList<>();
        if (!GetTeamQueryValidator.isValid(query, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        Team team;
        GetTeamQueryResult result = new GetTeamQueryResult();

        try {
            Optional<Team> existingTeam = teamRepository.findById(query.getId());

            if (existingTeam.isPresent()) {
                team = existingTeam.get();
            } else {
                throw new ApiErrorException("Team not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        result.setTeam(team);
        return result;
    }
}
