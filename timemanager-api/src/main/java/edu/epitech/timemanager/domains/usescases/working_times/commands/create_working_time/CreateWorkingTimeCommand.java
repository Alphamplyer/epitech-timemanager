package edu.epitech.timemanager.domains.usescases.working_times.commands.create_working_time;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CreateWorkingTimeCommand implements Command {
    private Integer userId;
    private Date startedAt;
    private Date endedAt;
}
