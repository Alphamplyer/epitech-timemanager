package edu.epitech.timemanager.presentation.api.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.epitech.timemanager.domains.models.enumerations.Role;

import java.util.Date;

public class UserView {

    private Integer id;

    private String username;

    private String email;

    private Role role;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
