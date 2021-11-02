package edu.epitech.timemanager.domains.usescases.teams.commands.add_user_to_team;

import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.persistence.TeamRepository;
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
public class AddUserToTeamCommandHandler implements CommandHandler<AddUserToTeamCommand> {

    private static Logger logger = LoggerFactory.getLogger(AddUserToTeamCommandHandler.class);

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddUserToTeamCommandHandler(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void execute(AddUserToTeamCommand command) {
        List<String> errors = new ArrayList<>();
        if (!AddUserToTeamCommandValidator.isValid(command, errors))
            throw new ApiErrorException("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);

        try {
            if (!userRepository.existsById(command.getUserId()))
                throw new ApiErrorException("User not found", HttpStatus.NOT_FOUND, errors);

            Optional<Team> existingTeam = teamRepository.findById(command.getTeamId());

            if (existingTeam.isPresent()) {
                Team team = existingTeam.get();
                team.addUser(new User(command.getUserId()));
            } else {
                throw new ApiErrorException("Team not found", HttpStatus.NOT_FOUND, errors);
            }
        } catch (HibernateException e) {
            logger.error("Failed to call database.", e);
            throw new ApiErrorException("Internal Error! Please retry or contact the support", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
