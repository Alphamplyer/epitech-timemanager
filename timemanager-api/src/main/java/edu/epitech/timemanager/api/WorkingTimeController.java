package edu.epitech.timemanager.api;

import edu.epitech.timemanager.domains.dto.workingtimes.CreateWorkingTimeDto;
import edu.epitech.timemanager.domains.dto.workingtimes.UpdateWorkingTimeDto;
import edu.epitech.timemanager.domains.mappers.WorkingTimeMappers;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.domains.utils.UserUtils;
import edu.epitech.timemanager.services.TeamService;
import edu.epitech.timemanager.services.WorkingTimeService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@CrossOrigin(origins = {"http://104.155.68.60:8080", "http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workingtimes")
public class WorkingTimeController {
    private final WorkingTimeMappers workingTimeMappers = Mappers.getMapper(WorkingTimeMappers.class);

    private final WorkingTimeService workingTimeService;
    private final TeamService teamService;

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getWorkingTimes(
        @AuthenticationPrincipal User user,
        @PathVariable("userId") Integer userId,
        @RequestParam(value = "start", required = false) Date start,
        @RequestParam(value = "end", required = false) Date end
    ) {
        if (!UserUtils.isTheSameUser(user, userId) && !UserUtils.isGlobalManager(user) && !UserUtils.isManagerOfUserTeam(user, userId, teamService))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (start == null && end == null) {
            return ResponseEntity.ok(workingTimeService.getWorkingTimes(userId));
        }
        else if (start != null && end != null) {
            return ResponseEntity.ok(workingTimeService.getWorkingTimes(userId, start, end));
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkingTime(@AuthenticationPrincipal User user, @PathVariable("id") Integer id) {
        WorkingTime workingTime = workingTimeService.getWorkingTime(id);

        if (workingTime != null) {
            if (!UserUtils.isTheSameUser(user, workingTime.getUser().getId()) && !UserUtils.isGlobalManager(user) && !UserUtils.isManagerOfUserTeam(user, workingTime.getUser().getId(), teamService))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            return ResponseEntity.ok(workingTimeMappers.workingTimeToWorkingTimeDto(workingTime));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createWorkingTime(
        @AuthenticationPrincipal User user,
        @RequestBody CreateWorkingTimeDto workingTimeDto
    ) {
        return new ResponseEntity<>(
            workingTimeMappers.workingTimeToWorkingTimeDto(
                workingTimeService.createWorkingTime(
                    new WorkingTime(
                        workingTimeDto.getStart(),
                        workingTimeDto.getEnd(),
                        user
                    )
                )
            ),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkingTime(
        @AuthenticationPrincipal User user,
        @PathVariable("id") int id,
        @RequestBody UpdateWorkingTimeDto workingTimeDto
    ) {
        try {
            WorkingTime workingTime = workingTimeService.updateWorkingTime(
                user.getId(),
                new WorkingTime(
                    id,
                    workingTimeDto.getStart(),
                    workingTimeDto.getEnd()
                )
            );

            if (workingTime == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return ResponseEntity.ok(workingTime);
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "UNAUTHORIZED"))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            else
                throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkingTime(
        @AuthenticationPrincipal User user,
        @PathVariable("id") int id
    ) {
        try {
            workingTimeService.deleteWorkingTime(id, user.getId());
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "UNAUTHORIZED"))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            else
                throw e;
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
