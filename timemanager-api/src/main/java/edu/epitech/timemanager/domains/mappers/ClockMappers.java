package edu.epitech.timemanager.domains.mappers;

import edu.epitech.timemanager.domains.dto.clocks.ClockDto;
import edu.epitech.timemanager.domains.models.Clock;
import org.mapstruct.Mapper;

@Mapper
public interface ClockMappers {
    ClockDto clockToClockDto(Clock clock);
}
