package edu.epitech.timemanager.presentation.api.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String identifier;
    private String password;

    @JsonProperty("remember_me")
    private Boolean rememberMe;
}
