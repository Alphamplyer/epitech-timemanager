package edu.epitech.timemanager.domains.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserDto {
    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    @Email
    private String email;
}
