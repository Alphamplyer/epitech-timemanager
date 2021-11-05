package edu.epitech.timemanager.domains.dto.workingtimes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class WorkingTimeDto {
    private Date start;
    private Date end;
}
