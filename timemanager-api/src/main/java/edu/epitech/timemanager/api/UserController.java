package edu.epitech.timemanager.api;

import edu.epitech.timemanager.domains.dto.users.CreateUserDto;
import edu.epitech.timemanager.domains.dto.users.UpdateUserDto;
import edu.epitech.timemanager.domains.mappers.UserMappers;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMappers userMappers = Mappers.getMapper(UserMappers.class);

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userMappers.userToUserDto(user));
    }

    @GetMapping("")
    public ResponseEntity<?> getUser(
        @RequestParam(value = "username", required = false) String username,
        @RequestParam(value = "email", required = false) String email
    ) {
        if (username == null && email == null) {
            return ResponseEntity.ok(userMappers.usersToUserDtos(userService.getUsers()));
        } else if (username != null && email != null) {
            User foundUser = userService.getUserByUsernameOrEmail(username, email);

            if (foundUser == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return ResponseEntity.ok(userMappers.userToUserDto(foundUser));
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto userDto) {
        return new ResponseEntity<>(
            userMappers.userToUserDto(userService.createUser(
                new User(
                    userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getPassword()
                )
            )),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserDto userDto) {
        User user = userService.updateUser(id, new User(
            id,
            userDto.getUsername(),
            userDto.getEmail()
        ));

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userMappers.userToUserDto(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}