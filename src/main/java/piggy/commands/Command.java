package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException;
    public abstract boolean isExit();
}