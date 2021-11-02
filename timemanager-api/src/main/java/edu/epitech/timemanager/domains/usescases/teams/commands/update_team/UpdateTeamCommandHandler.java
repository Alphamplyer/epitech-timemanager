package edu.epitech.timemanager.domains.usescases.teams.commands.update_team;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.persistence.TeamRepository;
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
public class UpdateTeamCommandHandler implements CommandHandler<UpdateTeamCommand> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateTeamCommandHandler.class);

    private final TeamRepository teamRepository;

    @Autowired
    public UpdateTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void execute(UpdateTeamCommand command) {
        List<String> errors = new ArrayList<>();
        if (!UpdateTeamCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            Optional<Team> existingTeam = teamRepository.findById(command.getId());

            if (existingTeam.isPresent()) {
                Team team = existingTeam.get();
                team.setName(command.getName());
                team.setDescription(command.getDescription());

                teamRepository.save(team);
            } else {
                throw new ApiErrorException("Team not found", HttpStatus.NOT_FOUND, null);
            }
        }
        catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
