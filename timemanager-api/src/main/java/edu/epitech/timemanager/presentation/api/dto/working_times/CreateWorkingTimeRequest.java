package edu.epitech.timemanager.presentation.api.dto.working_times;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkingTimeRequest {
    private Date startedAt;
    private Date endedAt;
}
