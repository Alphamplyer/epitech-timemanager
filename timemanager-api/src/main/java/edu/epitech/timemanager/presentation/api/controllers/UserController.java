package edu.epitech.timemanager.presentation.api.controllers;

import edu.epitech.timemanager.domains.usescases.users.commands.create_user.CreateUserCommand;
import edu.epitech.timemanager.domains.usescases.users.commands.create_user.CreateUserCommandHandler;
import edu.epitech.timemanager.domains.usescases.users.commands.delete_user.DeleteUserCommand;
import edu.epitech.timemanager.domains.usescases.users.commands.delete_user.DeleteUserCommandHandler;
import edu.epitech.timemanager.domains.usescases.users.commands.update_user.UpdateUserCommand;
import edu.epitech.timemanager.domains.usescases.users.commands.update_user.UpdateUserCommandHandler;
import edu.epitech.timemanager.domains.usescases.users.queries.get_all_users.GetAllUsersQuery;
import edu.epitech.timemanager.domains.usescases.users.queries.get_all_users.GetAllUsersQueryHandler;
import edu.epitech.timemanager.domains.usescases.users.queries.get_all_users.GetAllUsersQueryResult;
import edu.epitech.timemanager.domains.usescases.users.queries.get_user.GetUserQuery;
import edu.epitech.timemanager.domains.usescases.users.queries.get_user.GetUserQueryHandler;
import edu.epitech.timemanager.domains.usescases.users.queries.get_user.GetUserQueryResult;
import edu.epitech.timemanager.presentation.api.dto.users.CreateUserRequest;
import edu.epitech.timemanager.presentation.api.dto.users.UpdateUserRequest;
import edu.epitech.timemanager.presentation.api.dto.users.UserView;
import edu.epitech.timemanager.presentation.api.mappers.users.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final GetAllUsersQueryHandler getAllUsersQuery;
    private final GetUserQueryHandler getUserQuery;

    private final CreateUserCommandHandler createUserCommand;
    private final UpdateUserCommandHandler updateUserCommand;
    private final DeleteUserCommandHandler deleteUserCommand;

    @Autowired
    public UserController(
        GetAllUsersQueryHandler getAllUsersQuery,
        GetUserQueryHandler getUserQuery,
        CreateUserCommandHandler createUserCommand,
        UpdateUserCommandHandler updateUserCommand,
        DeleteUserCommandHandler deleteUserCommand
    ) {
        this.getAllUsersQuery = getAllUsersQuery;
        this.getUserQuery = getUserQuery;
        this.createUserCommand = createUserCommand;
        this.updateUserCommand = updateUserCommand;
        this.deleteUserCommand = deleteUserCommand;
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        GetAllUsersQueryResult result = getAllUsersQuery.execute(new GetAllUsersQuery());
        List<UserView> users = userMapper.userListToUserViewList(result.getUsers());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        GetUserQueryResult result = getUserQuery.execute(new GetUserQuery(id));
        UserView user = userMapper.userToUserView(result.getUser());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        createUserCommand.execute(
            new CreateUserCommand(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
            )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserRequest request) {
        updateUserCommand.execute(
            new UpdateUserCommand(
                id,
                request.getUsername(),
                request.getEmail()
            )
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        deleteUserCommand.execute(
            new DeleteUserCommand(id)
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
