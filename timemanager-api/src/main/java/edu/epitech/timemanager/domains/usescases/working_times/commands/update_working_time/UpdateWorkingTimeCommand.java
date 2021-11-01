package edu.epitech.timemanager.domains.usescases.working_times.commands.update_working_time;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class UpdateWorkingTimeCommand implements Command {
    private Integer id;
    private Date startedAt;
    private Date endedAt;
}
