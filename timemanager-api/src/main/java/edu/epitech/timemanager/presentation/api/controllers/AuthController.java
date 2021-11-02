package edu.epitech.timemanager.presentation.api.controllers;

import edu.epitech.timemanager.configurations.security.CustomUserDetails;
import edu.epitech.timemanager.configurations.security.CustomUserDetailsService;
import edu.epitech.timemanager.domains.usescases.auth.queries.getUserJwt.GetUserJwtQuery;
import edu.epitech.timemanager.domains.usescases.auth.queries.getUserJwt.GetUserJwtQueryHandler;
import edu.epitech.timemanager.domains.usescases.users.commands.create_user.CreateUserCommand;
import edu.epitech.timemanager.domains.usescases.users.commands.create_user.CreateUserCommandHandler;
import edu.epitech.timemanager.presentation.api.dto.auth.AuthenticationResponse;
import edu.epitech.timemanager.presentation.api.dto.auth.LoginRequest;
import edu.epitech.timemanager.presentation.api.dto.users.CreateUserRequest;
import edu.epitech.timemanager.shared.exceptions.ApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    private final GetUserJwtQueryHandler getUserJwtQuery;
    private final CreateUserCommandHandler createUserCommand;

    @Autowired
    public AuthController(
        AuthenticationManager authenticationManager,
        CustomUserDetailsService customUserDetailsService,
        GetUserJwtQueryHandler getUserJwtQuery,
        CreateUserCommandHandler createUserCommand
    ) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.getUserJwtQuery = getUserJwtQuery;
        this.createUserCommand = createUserCommand;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getPassword())
            );
        } catch (DisabledException e) {
            throw new ApiErrorException("User disabled", HttpStatus.UNAUTHORIZED, null);
        } catch (BadCredentialsException e) {
            throw new ApiErrorException("Invalid Credentials", HttpStatus.UNAUTHORIZED, null);
        }

        final CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(request.getIdentifier());
        final String token = getUserJwtQuery.execute(new GetUserJwtQuery(userDetails, request.getRememberMe())).getToken();
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest request) {
        createUserCommand.execute(
                new CreateUserCommand(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
