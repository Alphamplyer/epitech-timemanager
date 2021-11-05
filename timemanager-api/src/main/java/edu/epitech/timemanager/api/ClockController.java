package edu.epitech.timemanager.api;

import edu.epitech.timemanager.domains.mappers.ClockMappers;
import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.services.ClockService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clocks")
public class ClockController {
    private final ClockMappers clockMappers = Mappers.getMapper(ClockMappers.class);
    private final ClockService clockService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserClock(@PathVariable("userId") int userId) {
        Clock clock = clockService.getUserClock(userId);

        if (clock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(clockMappers.clockToClockDto(clock));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> toggleUserClock(@PathVariable("userId") int userId) {
        Clock clock = clockService.toggleUserClock(userId);

        if (clock == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(clockMappers.clockToClockDto(clock));
    }
}
