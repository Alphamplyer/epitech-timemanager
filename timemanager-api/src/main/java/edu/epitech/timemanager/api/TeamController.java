package edu.epitech.timemanager.api;

import edu.epitech.timemanager.domains.dto.teams.CreateTeamDto;
import edu.epitech.timemanager.domains.dto.teams.UpdateTeamDto;
import edu.epitech.timemanager.domains.mappers.TeamMappers;
import edu.epitech.timemanager.domains.mappers.UserMappers;
import edu.epitech.timemanager.domains.mappers.WorkingTimeMappers;
import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import edu.epitech.timemanager.services.TeamService;
import edu.epitech.timemanager.services.UserService;
import edu.epitech.timemanager.services.WorkingTimeService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://104.155.68.60:8080", "http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final UserMappers userMappers = Mappers.getMapper(UserMappers.class);
    private final TeamMappers teamMappers = Mappers.getMapper(TeamMappers.class);
    private final WorkingTimeMappers workingTimeMappers = Mappers.getMapper(WorkingTimeMappers.class);

    private final TeamService teamService;
    private final UserService userService;
    private final WorkingTimeService workingTimeService;

    @PreAuthorize("hasRole('ROLE_GLOBAL_MANAGER')")
    @GetMapping("")
    public ResponseEntity<?> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
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

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/{teamId}/workingtimes")
    public ResponseEntity<?> getTeamWorkingTime(@AuthenticationPrincipal User user, @PathVariable("teamId") int teamId) {
        if (user.getRole() == Role.MANAGER || teamService.isInTeam(user.getId(), teamId)) {
            List<WorkingTime> workingTimes = teamService.getTeamWorkingTimes(teamId);

            if (workingTimes == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return ResponseEntity.ok(workingTimeMappers.workingTimesToWorkingTimesDtos(workingTimes));
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/{teamId}/members")
    public ResponseEntity<?> getTeamMembers(@AuthenticationPrincipal User user, @PathVariable("teamId") int teamId) {
        if (user.getRole() == Role.GLOBAL_MANAGER || teamService.isInTeam(user.getId(), teamId)) {
            List<User> teamMembers = teamService.getTeamMembers(teamId);

            if (teamMembers == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return ResponseEntity.ok(userMappers.usersToUserDtos(teamMembers));
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/{teamId}/add/{userId}")
    public ResponseEntity<?> addUserToTeam(@PathVariable("teamId") int teamId, @PathVariable("userId") int userId) {
        boolean success = teamService.addUserToTeam(teamId, userId);

        if (!success)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/{teamId}/remove/{userId}")
    public ResponseEntity<?> removeUserFromTeam(@PathVariable("teamId") int teamId, @PathVariable("userId") int userId) {
        boolean success = teamService.removeUserFromTeam(teamId, userId);

        if (!success)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("")
    public ResponseEntity<?> createTeam(@AuthenticationPrincipal User user, @RequestBody CreateTeamDto team) {
        int authenticateUserId = user.getId();

        return ResponseEntity.ok(teamMappers.teamToTeamDTO(
            teamService.createTeam(authenticateUserId, new Team(
                team.getName(),
                team.getDescription()
            ))
        ));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable("teamId") int teamId, @RequestBody UpdateTeamDto team) {
        return ResponseEntity.ok(teamMappers.teamToTeamDTO(
                teamService.updateTeam(teamId, new Team(
                    team.getName(),
                    team.getDescription()
                ))
        ));
    }

    @PreAuthorize("hasRole('ROLE_GLOBAL_MANAGER')")
    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable("teamId") int teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
