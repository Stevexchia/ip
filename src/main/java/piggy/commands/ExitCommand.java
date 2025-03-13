package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        ui.showMessage(Constants.BYE_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}