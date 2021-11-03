package edu.epitech.timemanager.domains.usescases.teams.commands.delete_team;

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

@Service
public class DeleteTeamCommandHandler implements CommandHandler<DeleteTeamCommand> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteTeamCommandHandler.class);

    private final TeamRepository teamRepository;

    @Autowired
    public DeleteTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void execute(DeleteTeamCommand command) {
        List<String> errors = new ArrayList<>();
        if (!DeleteTeamCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            if (teamRepository.existsById(command.getId())) {
                teamRepository.deleteById(command.getId());
            } else {
                throw new ApiErrorException("Team not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
