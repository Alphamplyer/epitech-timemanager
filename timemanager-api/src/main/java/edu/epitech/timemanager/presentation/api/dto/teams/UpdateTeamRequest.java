package edu.epitech.timemanager.presentation.api.dto.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeamRequest {
    String name;
    String description;
}
