package edu.epitech.timemanager.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserDto {
    @NotNull
    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;
}
