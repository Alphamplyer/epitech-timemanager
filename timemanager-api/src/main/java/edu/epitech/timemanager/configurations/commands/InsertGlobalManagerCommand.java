package edu.epitech.timemanager.configurations.commands;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import edu.epitech.timemanager.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InsertGlobalManagerCommand implements ApplicationListener<ApplicationReadyEvent> {

    private final UserService userService;

    @Value("${timemanager.global-manager.username}")
    private String username;

    @Value("${timemanager.global-manager.email}")
    private String email;

    @Value("${timemanager.global-manager.password}")
    private String password;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        try {
            User user = new User(null, username, email, password, Role.GLOBAL_MANAGER);
            userService.createUser(user);
            log.info("user '{}' created with email '{}'", username, email);
        } catch (Exception e) {
            log.info("Global manager already exist or database is not reachable");
        }
    }
}
