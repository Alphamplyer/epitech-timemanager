package edu.epitech.timemanager.domains.usescases.teams.commands.create_team;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CreateTeamCommandHandler implements CommandHandler<CreateTeamCommand> {

    private final Logger logger = LoggerFactory.getLogger(CreateTeamCommandHandler.class);

    private final TeamRepository teamRepository;

    @Autowired
    public CreateTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void execute(CreateTeamCommand command) {
        List<String> errors = new ArrayList<>();
        if (!CreateTeamCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        Team team = new Team();
        team.setName(command.getName());
        team.setDescription(command.getDescription());
        Set<User> members = new HashSet<>();
        members.add(new User(command.getUserId()));
        team.setMembers(members);

        try {
            teamRepository.save(team);
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
