package piggy.commands;

import piggy.data.TaskList;
import piggy.task.Task;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String arguments) throws PiggyException {
        try {
            this.index = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new PiggyException("Oink! Please enter a valid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        try {
            Task removedTask = tasks.get(index); // Get the task before deleting it
            tasks.deleteTask(index);
            storage.save(tasks.getTasks());

            ui.showMessage(Constants.TASK_DELETED_MESSAGE);
            ui.showMessage("  " + removedTask);
            ui.showMessage(String.format(Constants.TASKS_COUNT_MESSAGE, tasks.size()));
        } catch (PiggyException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}