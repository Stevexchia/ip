package piggy.commands;

import piggy.data.TaskList;
import piggy.task.Task;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(String arguments, boolean isDone) throws PiggyException {
        try {
            this.index = Integer.parseInt(arguments.trim()) - 1; // Adjust for 0-based index
            this.isDone = isDone;
        } catch (NumberFormatException e) {
            throw new PiggyException("Oink! Please enter a valid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        try {
            tasks.markTask(index, isDone);
            Task task = tasks.get(index); // Get the task after it's been marked/unmarked
            if (isDone) {
                ui.showMessage("Nice! I've marked this task as done:\n  " + task);
            } else {
                ui.showMessage("Okay! I've unmarked this task:\n  " + task);
            }
            storage.save(tasks.getTasks());
        } catch (PiggyException e) {
            ui.showError(e.getMessage()); // Display the error message
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
