package edu.epitech.timemanager.domains.dto.workingtimes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UpdateWorkingTimeDto {
    @NotNull
    private Date start;

    @NotNull
    private Date end;
}
