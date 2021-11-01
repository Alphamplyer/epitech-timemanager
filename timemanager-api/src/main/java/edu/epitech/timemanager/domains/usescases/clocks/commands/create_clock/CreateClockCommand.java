package edu.epitech.timemanager.domains.usescases.clocks.commands.create_clock;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateClockCommand implements Command {
    private Integer userId;
}
