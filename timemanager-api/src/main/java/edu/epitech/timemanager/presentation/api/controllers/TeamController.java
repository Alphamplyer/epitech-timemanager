package edu.epitech.timemanager.presentation.api.controllers;

import edu.epitech.timemanager.domains.usescases.teams.commands.add_user_to_team.AddUserToTeamCommand;
import edu.epitech.timemanager.domains.usescases.teams.commands.add_user_to_team.AddUserToTeamCommandHandler;
import edu.epitech.timemanager.domains.usescases.teams.commands.create_team.CreateTeamCommand;
import edu.epitech.timemanager.domains.usescases.teams.commands.create_team.CreateTeamCommandHandler;
import edu.epitech.timemanager.domains.usescases.teams.commands.delete_team.DeleteTeamCommand;
import edu.epitech.timemanager.domains.usescases.teams.commands.delete_team.DeleteTeamCommandHandler;
import edu.epitech.timemanager.domains.usescases.teams.commands.remove_user_from_team.RemoveUserFromTeamCommand;
import edu.epitech.timemanager.domains.usescases.teams.commands.remove_user_from_team.RemoveUserFromTeamCommandHandler;
import edu.epitech.timemanager.domains.usescases.teams.commands.update_team.UpdateTeamCommand;
import edu.epitech.timemanager.domains.usescases.teams.commands.update_team.UpdateTeamCommandHandler;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_team.GetTeamQuery;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_team.GetTeamQueryHandler;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_team.GetTeamQueryResult;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_teams.GetTeamsQuery;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_teams.GetTeamsQueryHandler;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_teams.GetTeamsQueryResult;
import edu.epitech.timemanager.presentation.api.dto.teams.CreateTeamRequest;
import edu.epitech.timemanager.presentation.api.dto.teams.UpdateTeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final AddUserToTeamCommandHandler addUserToTeamCommand;
    private final CreateTeamCommandHandler createTeamCommand;
    private final DeleteTeamCommandHandler deleteTeamCommand;
    private final RemoveUserFromTeamCommandHandler removeUserFromTeamCommand;
    private final UpdateTeamCommandHandler updateTeamCommand;

    private final GetTeamQueryHandler getTeamQuery;
    private final GetTeamsQueryHandler getTeamsQuery;

    @Autowired
    public TeamController(
            AddUserToTeamCommandHandler addUserToTeamCommand,
            CreateTeamCommandHandler createTeamCommand,
            DeleteTeamCommandHandler deleteTeamCommand,
            RemoveUserFromTeamCommandHandler removeUserFromTeamCommand,
            UpdateTeamCommandHandler updateTeamCommand,
            GetTeamQueryHandler getTeamQuery,
            GetTeamsQueryHandler getTeamsQuery) {
        this.addUserToTeamCommand = addUserToTeamCommand;
        this.createTeamCommand = createTeamCommand;
        this.deleteTeamCommand = deleteTeamCommand;
        this.removeUserFromTeamCommand = removeUserFromTeamCommand;
        this.updateTeamCommand = updateTeamCommand;
        this.getTeamQuery = getTeamQuery;
        this.getTeamsQuery = getTeamsQuery;
    }

    @GetMapping("")
    public ResponseEntity<?> getTeams() {
        GetTeamsQueryResult result = getTeamsQuery.execute(
                new GetTeamsQuery()
        );
        return new ResponseEntity<>(result.getTeams(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeam(@PathVariable("id") int id) {
        GetTeamQueryResult result = getTeamQuery.execute(
                new GetTeamQuery(id)
        );
        return new ResponseEntity<>(result.getTeam(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamRequest request) {
        createTeamCommand.execute(
                new CreateTeamCommand(
                        request.getUserId(),
                        request.getName(),
                        request.getDescription()
                )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable("id") int id, @RequestBody UpdateTeamRequest request) {
        updateTeamCommand.execute(
                new UpdateTeamCommand(
                        id,
                        request.getName(),
                        request.getDescription()
                )
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") int id) {
        deleteTeamCommand.execute(
                new DeleteTeamCommand(id)
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{teamId}/add")
    public ResponseEntity<?> addUserToTeam(@PathVariable("teamId") int teamId, @RequestParam("userId") int userId) {
        addUserToTeamCommand.execute(
                new AddUserToTeamCommand(userId, teamId)
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{teamId}/remove")
    public ResponseEntity<?> removeUserFromTeam(@PathVariable("teamId") int teamId, @RequestParam("userId") int userId) {
        removeUserFromTeamCommand.execute(
                new RemoveUserFromTeamCommand(userId, teamId)
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
