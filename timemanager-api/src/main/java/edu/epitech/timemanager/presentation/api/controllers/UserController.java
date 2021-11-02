package edu.epitech.timemanager.presentation.api.controllers;

import edu.epitech.timemanager.domains.usescases.teams.queries.get_user_teams.GetUserTeamsQuery;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_user_teams.GetUserTeamsQueryHandler;
import edu.epitech.timemanager.domains.usescases.teams.queries.get_user_teams.GetUserTeamsQueryResult;
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
import edu.epitech.timemanager.presentation.api.dto.users.UpdateUserRequest;
import edu.epitech.timemanager.presentation.api.dto.users.UserView;
import edu.epitech.timemanager.presentation.api.mappers.users.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final GetAllUsersQueryHandler getAllUsersQuery;
    private final GetUserQueryHandler getUserQuery;
    private final GetUserTeamsQueryHandler getUserTeamsQuery;

    private final UpdateUserCommandHandler updateUserCommand;
    private final DeleteUserCommandHandler deleteUserCommand;

    @Autowired
    public UserController(
            GetAllUsersQueryHandler getAllUsersQuery,
            GetUserQueryHandler getUserQuery,
            GetUserTeamsQueryHandler getUserTeamsQuery,
            UpdateUserCommandHandler updateUserCommand,
            DeleteUserCommandHandler deleteUserCommand
    ) {
        this.getAllUsersQuery = getAllUsersQuery;
        this.getUserQuery = getUserQuery;
        this.getUserTeamsQuery = getUserTeamsQuery;
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

    @GetMapping("/{userId}/teams")
    public ResponseEntity<?> getUserTeams(@PathVariable("userId") int userId) {
        GetUserTeamsQueryResult result = getUserTeamsQuery.execute(
                new GetUserTeamsQuery(userId)
        );
        return new ResponseEntity<>(result.getTeams(), HttpStatus.OK);
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
