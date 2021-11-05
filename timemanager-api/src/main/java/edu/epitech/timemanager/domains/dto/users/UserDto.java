package edu.epitech.timemanager.domains.dto.users;

import edu.epitech.timemanager.domains.models.enumerations.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private Role role;
}
