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
import edu.epitech.timemanager.domains.utils.exceptions.NotFoundException;
import edu.epitech.timemanager.domains.utils.exceptions.UnauthorizedException;
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
        return ResponseEntity.ok(teamMappers.teamsToTeamDTOs(teamService.getTeams()));
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserTeams(@AuthenticationPrincipal User user, @PathVariable("userId") int userId) {
        if (user.getId() == userId)
            throw new UnauthorizedException("The given ID must be your ID");

        switch (user.getRole()) {
            case MANAGER:
                return ResponseEntity.ok(teamMappers.teamsToTeamDTOs(teamService.getUserTeams(userId)));
            case GLOBAL_MANAGER:
                return ResponseEntity.ok(teamMappers.teamsToTeamDTOs(teamService.getTeams()));
            case EMPLOYEE:
                return ResponseEntity.ok(teamMappers.teamToTeamDTO(teamService.getUserTeam(userId)));
            default:
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/{teamId}/workingtimes")
    public ResponseEntity<?> getTeamWorkingTime(@AuthenticationPrincipal User user, @PathVariable("teamId") int teamId) {
        if (user.getRole() != Role.MANAGER && !teamService.isInTeam(user.getId(), teamId)) {
            throw new UnauthorizedException("User must be in a team and have a the 'Manager' role");
        }

        return ResponseEntity.ok(
            workingTimeMappers.workingTimesToWorkingTimesDtos(
                teamService.getTeamWorkingTimes(teamId)
            )
        );
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/{teamId}/members")
    public ResponseEntity<?> getTeamMembers(@AuthenticationPrincipal User user, @PathVariable("teamId") int teamId) {
        if (user.getRole() != Role.MANAGER && !teamService.isInTeam(user.getId(), teamId)) {
            throw new UnauthorizedException("User must be in a team and have a the 'Manager' role");
        }

        return ResponseEntity.ok(
            userMappers.usersToUserDtos(
                teamService.getTeamMembers(teamId)
            )
        );
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/{teamId}/add/{userId}")
    public ResponseEntity<?> addUserToTeam(
        @AuthenticationPrincipal User user,
        @PathVariable("teamId") int teamId,
        @PathVariable("userId") int userId
    ) {
        if (user.getRole() != Role.MANAGER && !teamService.isInTeam(user.getId(), teamId)) {
            throw new UnauthorizedException("User must be in a team and have a the 'Manager' role");
        }

        teamService.addUserToTeam(userId, teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/{teamId}/remove/{userId}")
    public ResponseEntity<?> removeUserFromTeam(
        @AuthenticationPrincipal User user,
        @PathVariable("teamId") int teamId,
        @PathVariable("userId") int userId
    ) {
        if (user.getRole() != Role.MANAGER && !teamService.isInTeam(user.getId(), teamId)) {
            throw new UnauthorizedException("User must be in a team and have a the 'Manager' role");
        }

        teamService.removeUserFromTeam(userId, teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("")
    public ResponseEntity<?> createTeam(
        @AuthenticationPrincipal User user,
        @RequestBody CreateTeamDto team
    ) {
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
    public ResponseEntity<?> updateTeam(
        @AuthenticationPrincipal User user,
        @PathVariable("teamId") int teamId,
        @RequestBody UpdateTeamDto team
    ) {
        if (user.getRole() != Role.MANAGER && !teamService.isInTeam(user.getId(), teamId)) {
            throw new UnauthorizedException("User must be in a team and have a the 'Manager' role");
        }

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
