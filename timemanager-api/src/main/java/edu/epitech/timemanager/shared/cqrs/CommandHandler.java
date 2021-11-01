package edu.epitech.timemanager.shared.cqrs;

public interface CommandHandler<TCommand extends Command> {
    public void execute(TCommand command);
}
