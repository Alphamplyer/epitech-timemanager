package edu.epitech.timemanager.presentation.api.dto.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamRequest {
    private Integer userId;
    private String name;
    private String description;
}
