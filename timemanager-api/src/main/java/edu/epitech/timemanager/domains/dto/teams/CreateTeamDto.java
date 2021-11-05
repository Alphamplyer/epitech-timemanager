package edu.epitech.timemanager.domains.dto.teams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTeamDto {
    private String name;
    private String description;
}
