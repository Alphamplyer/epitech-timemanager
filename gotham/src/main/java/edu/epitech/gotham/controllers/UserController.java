package edu.epitech.gotham.controllers;

import edu.epitech.gotham.models.User;
import edu.epitech.gotham.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> getUserByEmailOrUsername(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "username", required = false) String username
    ) {
        User user;

        if (email.isEmpty() && username.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else if (username.isEmpty())
            user = userService.getUserByEmail(email);
        else
            user = userService.getUserByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.updateUser(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
