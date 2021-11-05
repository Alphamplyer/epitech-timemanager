package edu.epitech.timemanager.domains.dto.teams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateTeamDto {
    private String name;
    private String description;
}
