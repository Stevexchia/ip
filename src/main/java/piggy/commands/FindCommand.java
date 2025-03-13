package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) throws PiggyException {
        if (keyword.trim().isEmpty()) {
            throw new PiggyException(Constants.INVALID_FIND_COMMAND);
        }
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        tasks.findTasksByKeyword(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}