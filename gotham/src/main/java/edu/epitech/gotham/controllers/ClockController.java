package edu.epitech.gotham.controllers;

import edu.epitech.gotham.models.Clock;
import edu.epitech.gotham.services.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api")
public class ClockController {

    private final ClockService clockService;

    @Autowired
    public ClockController(ClockService clockService) {
        this.clockService = clockService;
    }

    @GetMapping("/clocks/{userId}")
    public ResponseEntity<?> getUserClock(@PathVariable("userId") int userId) {
        Clock clock = clockService.getUserClock(userId);
        return new ResponseEntity<>(clock, HttpStatus.OK);
    }

    @PostMapping("/clocks/{userId}")
    public ResponseEntity<?> toggleUserClock(@PathVariable("userId") int userId) {
        Clock clock = clockService.toggleUserClock(userId);
        return new ResponseEntity<>(clock, HttpStatus.OK);
    }
}
