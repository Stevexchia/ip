package piggy.commands;

import piggy.data.TaskList;
import piggy.task.Task;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(String arguments, boolean isDone) throws PiggyException {
        try {
            this.index = Integer.parseInt(arguments.trim()) - Constants.TASK_INDEX_OFFSET; // Adjust for 0-based index
            this.isDone = isDone;
        } catch (NumberFormatException e) {
            throw new PiggyException(Constants.INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        try {
            tasks.markTask(index, isDone);
            Task task = tasks.get(index); // Get the task after it's been marked/unmarked
            if (isDone) {
                ui.showMessage(Constants.TASK_MARKED_MESSAGE);
            } else {
                ui.showMessage(Constants.TASK_UNMARKED_MESSAGE);
            }
            ui.showMessage("  " + task);
            storage.save(tasks.getTasks());
        } catch (PiggyException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
