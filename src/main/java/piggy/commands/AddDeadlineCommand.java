package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.task.Deadline;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String arguments) throws PiggyException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new PiggyException("Oink! Invalid format! Use: deadline <description> /by <time>");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());

        ui.showMessage("Oink! This task is now in my snout:");
        ui.showMessage("  " + deadline);
        ui.showMessage("Snort! You’ve got " + tasks.size() + " tasks in your pen.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}