package edu.epitech.timemanager.domains.mappers;

import edu.epitech.timemanager.domains.dto.workingtimes.WorkingTimeDto;
import edu.epitech.timemanager.domains.models.WorkingTime;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WorkingTimeMappers {
    WorkingTimeDto workingTimeToWorkingTimeDto(WorkingTime workingTime);
    List<WorkingTimeDto> workingTimesToWorkingTimesDtos(List<WorkingTime> workingTimes);
}
