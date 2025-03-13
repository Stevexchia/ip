package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.task.Event;
import piggy.util.Constants;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String arguments) throws PiggyException {
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new PiggyException(Constants.INVALID_FORMAT_EVENT_MESSAGE);
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    /**
     * Executes the command to add an event task.
     *
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui to interact with the user.
     * @param storage The Storage to save the tasks.
     * @throws PiggyException If there is an error adding the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        storage.save(tasks.getTasks());

        ui.showMessage(Constants.TASK_ADDED_MESSAGE);
        ui.showMessage("  " + event);
        ui.showMessage(String.format(Constants.TASKS_COUNT_MESSAGE, tasks.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}