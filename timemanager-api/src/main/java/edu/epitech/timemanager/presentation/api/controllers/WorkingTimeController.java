package edu.epitech.timemanager.presentation.api.controllers;

import edu.epitech.timemanager.domains.usescases.working_times.commands.create_working_time.CreateWorkingTimeCommand;
import edu.epitech.timemanager.domains.usescases.working_times.commands.create_working_time.CreateWorkingTimeCommandHandler;
import edu.epitech.timemanager.domains.usescases.working_times.commands.delete_working_time.DeleteWorkingTimeCommand;
import edu.epitech.timemanager.domains.usescases.working_times.commands.delete_working_time.DeleteWorkingTimeCommandHandler;
import edu.epitech.timemanager.domains.usescases.working_times.commands.update_working_time.UpdateWorkingTimeCommand;
import edu.epitech.timemanager.domains.usescases.working_times.commands.update_working_time.UpdateWorkingTimeCommandHandler;
import edu.epitech.timemanager.domains.usescases.working_times.queries.get_user_working_times.GetUserWorkingTimesQuery;
import edu.epitech.timemanager.domains.usescases.working_times.queries.get_user_working_times.GetUserWorkingTimesQueryHandler;
import edu.epitech.timemanager.domains.usescases.working_times.queries.get_user_working_times.GetUserWorkingTimesQueryResult;
import edu.epitech.timemanager.domains.usescases.working_times.queries.get_working_time.GetWorkingTimeQuery;
import edu.epitech.timemanager.domains.usescases.working_times.queries.get_working_time.GetWorkingTimeQueryHandler;
import edu.epitech.timemanager.domains.usescases.working_times.queries.get_working_time.GetWorkingTimeQueryResult;
import edu.epitech.timemanager.presentation.api.dto.working_times.CreateWorkingTimeRequest;
import edu.epitech.timemanager.presentation.api.dto.working_times.UpdateWorkingTimeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/workingtimes")
public class WorkingTimeController {

    private final GetUserWorkingTimesQueryHandler getUserWorkingTimesQuery;
    private final GetWorkingTimeQueryHandler getWorkingTimeQuery;

    private final CreateWorkingTimeCommandHandler createWorkingTimeCommand;
    private final UpdateWorkingTimeCommandHandler updateWorkingTimeCommand;
    private final DeleteWorkingTimeCommandHandler deleteWorkingTimeCommand;

    public WorkingTimeController(
        GetUserWorkingTimesQueryHandler getUserWorkingTimesQuery,
        GetWorkingTimeQueryHandler getWorkingTimeQuery,
        CreateWorkingTimeCommandHandler createWorkingTimeCommand,
        UpdateWorkingTimeCommandHandler updateWorkingTimeCommand,
        DeleteWorkingTimeCommandHandler deleteWorkingTimeCommand
    ) {
        this.getUserWorkingTimesQuery = getUserWorkingTimesQuery;
        this.getWorkingTimeQuery = getWorkingTimeQuery;
        this.createWorkingTimeCommand = createWorkingTimeCommand;
        this.updateWorkingTimeCommand = updateWorkingTimeCommand;
        this.deleteWorkingTimeCommand = deleteWorkingTimeCommand;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserWorkingTimes(
            @PathVariable("userId") int userId,
            @RequestParam("start") Date start,
            @RequestParam("end") Date end
    ) {
        GetUserWorkingTimesQueryResult result = getUserWorkingTimesQuery.execute(
          new GetUserWorkingTimesQuery(userId, start, end)
        );
        return new ResponseEntity<>(result.getWorkingTimes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkingTime(@PathVariable("id") int id) {
        GetWorkingTimeQueryResult result = getWorkingTimeQuery.execute(
          new GetWorkingTimeQuery(id)
        );
        return new ResponseEntity<>(result.getWorkingTime(), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createWorkingTime(@PathVariable("userId") int userId, @RequestBody CreateWorkingTimeRequest request) {
        createWorkingTimeCommand.execute(
            new CreateWorkingTimeCommand(
                userId,
                request.getStartedAt(),
                request.getEndedAt()
            )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkingTime(@PathVariable("id") int id, @RequestBody UpdateWorkingTimeRequest request) {
        updateWorkingTimeCommand.execute(
            new UpdateWorkingTimeCommand(
                id,
                request.getStartedAt(),
                request.getEndedAt()
            )
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkingTime(@PathVariable("id") int id) {
        deleteWorkingTimeCommand.execute(
            new DeleteWorkingTimeCommand(id)
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
