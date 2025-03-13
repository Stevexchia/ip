package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.task.Event;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String arguments) throws PiggyException {
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new PiggyException("Oink! Invalid format! Use: event <description> /from <start> /to <end>");
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        storage.save(tasks.getTasks());

        ui.showMessage("Oink! This task is now in my snout:");
        ui.showMessage("  " + event);
        ui.showMessage("Snort! You’ve got " + tasks.size() + " tasks in your pen.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}