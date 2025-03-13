package piggy.commands;

import piggy.data.TaskList;
import piggy.task.Task;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;

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

            ui.showMessage("Snort! This task is forgotten in the haystack:");
            ui.showMessage("  " + removedTask);
            ui.showMessage("Snort! You’ve got " + tasks.size() + " tasks in your pen.");
        } catch (PiggyException e) {
            ui.showError(e.getMessage()); // Display the error message
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}