package edu.epitech.timemanager.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;

    private final UserRepository userRepository;
    private final UserService userService;

    private final User USER_1 = new User("user1", "user1@timemanager.com", "password");

    @Test
    private void createUser_success() throws Exception {
        Mockito.when(userRepository.save(USER_1)).thenReturn(
            new User(1, "user1", "user1@timemanager.com", "$2a$10$fkRdqsjEh7J9aAJQsCVaneDX795fNOjoFutPv7QqIMg0wnBxCe9Cq", Role.EMPLOYEE)
        );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(USER_1));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.username", is("user1")))
            .andExpect(jsonPath("$.email", is("user1@timemanager.com")))
            .andExpect(jsonPath("$.role", is("EMPLOYEE")));
    }
}
