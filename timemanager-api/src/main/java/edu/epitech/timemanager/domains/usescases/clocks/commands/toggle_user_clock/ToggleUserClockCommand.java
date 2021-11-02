package edu.epitech.timemanager.domains.usescases.clocks.commands.toggle_user_clock;

import edu.epitech.timemanager.shared.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToggleUserClockCommand implements Command {
    private Integer userId;
}
