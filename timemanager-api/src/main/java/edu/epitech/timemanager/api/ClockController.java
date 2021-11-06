package edu.epitech.timemanager.api;

import edu.epitech.timemanager.domains.mappers.ClockMappers;
import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.services.ClockService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://104.155.68.60:8080", "http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clocks")
public class ClockController {
    private final ClockMappers clockMappers = Mappers.getMapper(ClockMappers.class);
    private final ClockService clockService;

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserClock(@AuthenticationPrincipal User user, @PathVariable("userId") int userId) {
        if (user.getId() != userId)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Clock clock = clockService.getUserClock(userId);

        if (clock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(clockMappers.clockToClockDto(clock));
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/{userId}")
    public ResponseEntity<?> toggleUserClock(@AuthenticationPrincipal User user, @PathVariable("userId") int userId) {
        if (user.getId() != userId)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Clock clock = clockService.toggleUserClock(userId);

        if (clock == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(clockMappers.clockToClockDto(clock));
    }
}
