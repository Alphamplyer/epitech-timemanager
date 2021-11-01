package edu.epitech.timemanager.presentation.api.controllers;

import edu.epitech.timemanager.domains.usescases.clocks.commands.toggle_user_clock.ToggleUserClockCommand;
import edu.epitech.timemanager.domains.usescases.clocks.commands.toggle_user_clock.ToggleUserClockCommandHandler;
import edu.epitech.timemanager.domains.usescases.clocks.queries.get_user_clock.GetUserClockQuery;
import edu.epitech.timemanager.domains.usescases.clocks.queries.get_user_clock.GetUserClockQueryHandler;
import edu.epitech.timemanager.domains.usescases.clocks.queries.get_user_clock.GetUserClockQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clocks")
public class ClockController {

    private final GetUserClockQueryHandler getUserClockQuery;
    private final ToggleUserClockCommandHandler toggleUserClockCommand;

    @Autowired
    public ClockController(
        GetUserClockQueryHandler getUserClockQuery,
        ToggleUserClockCommandHandler toggleUserClockCommand
    ) {
        this.getUserClockQuery = getUserClockQuery;
        this.toggleUserClockCommand = toggleUserClockCommand;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserClock(@PathVariable("userId") int userId) {
        GetUserClockQueryResult result = getUserClockQuery.execute(new GetUserClockQuery(userId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> toggleUserClock(@PathVariable("userId") int userId) {
        toggleUserClockCommand.execute(new ToggleUserClockCommand(userId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
