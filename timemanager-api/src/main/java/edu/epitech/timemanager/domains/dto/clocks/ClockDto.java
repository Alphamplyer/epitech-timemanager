package edu.epitech.timemanager.domains.dto.clocks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ClockDto {
    @JsonProperty("enabled_at")
    Date enabledAt;

    @JsonProperty("is_enable")
    boolean isEnable;
}
