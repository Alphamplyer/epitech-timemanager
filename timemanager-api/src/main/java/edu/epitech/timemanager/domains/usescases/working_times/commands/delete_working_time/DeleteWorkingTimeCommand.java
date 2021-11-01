package edu.epitech.timemanager.domains.usescases.working_times.commands.delete_working_time;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteWorkingTimeCommand implements Command {
    private Integer id;
}
