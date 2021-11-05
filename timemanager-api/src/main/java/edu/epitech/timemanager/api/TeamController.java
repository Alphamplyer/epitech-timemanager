package edu.epitech.timemanager.api;

import edu.epitech.timemanager.domains.dto.teams.CreateTeamDto;
import edu.epitech.timemanager.domains.dto.teams.UpdateTeamDto;
import edu.epitech.timemanager.domains.mappers.TeamMappers;
import edu.epitech.timemanager.domains.mappers.UserMappers;
import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.services.TeamService;
import edu.epitech.timemanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final UserMappers userMappers = Mappers.getMapper(UserMappers.class);
    private final TeamMappers teamMappers = Mappers.getMapper(TeamMappers.class);

    private final TeamService teamService;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserTeams(@PathVariable("userId") int userId) {
        User user = userService.getUser(userId);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        switch (user.getRole()) {
            case MANAGER:
                return ResponseEntity.ok(teamMappers.teamsToTeamDTOs(teamService.getUserTeams(userId)));
            case GLOBAL_MANAGER:
                return ResponseEntity.ok(teamMappers.teamsToTeamDTOs(teamService.getTeams()));
            case EMPLOYEE:
                Team team = teamService.getUserTeam(userId);

                if (team == null)
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

                return ResponseEntity.ok(teamMappers.teamToTeamDTO(team));
            default:
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{teamId}/members")
    public ResponseEntity<?> getTeamMembers(@PathVariable("teamId") int teamId) {
        return ResponseEntity.ok(userMappers.usersToUserDtos(teamService.getTeamMembers(teamId)));
    }

    @PostMapping("/{teamId}/add/{userId}")
    public ResponseEntity<?> addUserToTeam(@PathVariable("teamId") int teamId, @PathVariable("userId") int userId) {
        boolean success = teamService.addUserToTeam(teamId, userId);

        if (!success)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{teamId}/remove/{userId}")
    public ResponseEntity<?> removeUserFromTeam(@PathVariable("teamId") int teamId, @PathVariable("userId") int userId) {
        boolean success = teamService.removeUserFromTeam(teamId, userId);

        if (!success)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamDto team) {
        // TODO: initialize authenticate userId with the right id
        int authenticateUserId = 1;

        return ResponseEntity.ok(teamMappers.teamToTeamDTO(
            teamService.createTeam(authenticateUserId, new Team(
                team.getName(),
                team.getDescription()
            ))
        ));
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable("teamId") int teamId, @RequestBody UpdateTeamDto team) {
        return ResponseEntity.ok(teamMappers.teamToTeamDTO(
                teamService.updateTeam(teamId, new Team(
                    team.getName(),
                    team.getDescription()
                ))
        ));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable("teamId") int teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
