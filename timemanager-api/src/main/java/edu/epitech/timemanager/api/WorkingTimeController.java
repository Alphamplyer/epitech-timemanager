package edu.epitech.timemanager.api;

import edu.epitech.timemanager.domains.dto.workingtimes.CreateWorkingTimeDto;
import edu.epitech.timemanager.domains.dto.workingtimes.UpdateWorkingTimeDto;
import edu.epitech.timemanager.domains.mappers.WorkingTimeMappers;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.services.WorkingTimeService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workingtimes")
public class WorkingTimeController {
    private final WorkingTimeMappers workingTimeMappers = Mappers.getMapper(WorkingTimeMappers.class);
    private final WorkingTimeService workingTimeService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getWorkingTimes(
        @PathVariable("userId") Integer userId,
        @RequestParam(value = "start", required = false) Date start,
        @RequestParam(value = "end", required = false) Date end
    ) {
        if (start == null && end == null) {
            return ResponseEntity.ok(workingTimeService.getWorkingTimes(userId));
        }
        else if (start != null && end != null) {
            return ResponseEntity.ok(workingTimeService.getWorkingTimes(userId, start, end));
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkingTime(@PathVariable("id") Integer id) {
        WorkingTime workingTime = workingTimeService.getWorkingTime(id);

        if (workingTime == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(workingTimeMappers.workingTimeToWorkingTimeDto(workingTime));
    }

    @PostMapping("")
    public ResponseEntity<?> createWorkingTime(
        @RequestBody CreateWorkingTimeDto workingTimeDto
    ) {
        return new ResponseEntity<>(
            workingTimeMappers.workingTimeToWorkingTimeDto(
                workingTimeService.createWorkingTime(
                    new WorkingTime(
                        workingTimeDto.getStart(),
                        workingTimeDto.getEnd()
                    )
                )
            ),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkingTime(
        @PathVariable("id") int id,
        @RequestBody UpdateWorkingTimeDto workingTimeDto
    ) {
        WorkingTime workingTime = workingTimeService.updateWorkingTime(
            id,
            new WorkingTime(
                workingTimeDto.getStart(),
                workingTimeDto.getEnd()
            )
        );

        if (workingTime == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(workingTime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkingTime(
        @PathVariable("id") int id
    ) {
        workingTimeService.deleteWorkingTime(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
