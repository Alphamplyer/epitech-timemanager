package edu.epitech.gotham.controllers;

import edu.epitech.gotham.models.WorkingTime;
import edu.epitech.gotham.services.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api")
public class WorkingTimeController {

    private final WorkingTimeService workingTimeService;

    @Autowired
    public WorkingTimeController(WorkingTimeService workingTimeService) {
        this.workingTimeService = workingTimeService;
    }

    @GetMapping("/users/{userId}/workingtimes")
    public ResponseEntity<?> getUserWorkingTimes(
            @PathVariable("userId") int userId,
            @RequestParam("start") Timestamp start,
            @RequestParam("start") Timestamp end
    ) {
        List<WorkingTime> workingTimes = workingTimeService.getUserWorkingTimes(userId, start, end);
        return new ResponseEntity<>(workingTimes, HttpStatus.OK);
    }

    @GetMapping("/workingtimes/{id}")
    public ResponseEntity<?> getWorkingTime(@PathVariable("id") int id) {
        WorkingTime workingTime = workingTimeService.getWorkingTimeById(id);
        return new ResponseEntity<>(workingTime, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/workingTime")
    public ResponseEntity<?> createWorkingTime(@PathVariable("userId") int userId, @RequestBody WorkingTime workingTime) {
        workingTimeService.createWorkingTime(workingTime, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/workingtimes/{id}")
    public ResponseEntity<?> updateWorkingTime(@PathVariable("id") int id, @RequestBody WorkingTime workingTime) {
        workingTimeService.updateWorkingTime(workingTime, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/workingtimes/{id}")
    public ResponseEntity<?> deleteWorkingTime(@PathVariable("id") int id) {
        workingTimeService.deleteWorkingTime(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
