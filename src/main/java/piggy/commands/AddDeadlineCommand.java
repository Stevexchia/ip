package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.task.Deadline;
import piggy.util.Constants;

/**
 * Represents a command to add a deadline task.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public AddDeadlineCommand(String arguments) throws PiggyException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new PiggyException(Constants.INVALID_FORMAT_DEADLINE_MESSAGE);
        }
        this.description = parts[0].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME);
            this.by = LocalDateTime.parse(parts[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new PiggyException(Constants.INVALID_DATE_TIME);
        }
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui to interact with the user.
     * @param storage The Storage to save the tasks.
     * @throws PiggyException If there is an error adding the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());

        ui.showMessage(Constants.TASK_ADDED_MESSAGE);
        ui.showMessage("  " + deadline);
        ui.showMessage(String.format(Constants.TASKS_COUNT_MESSAGE, tasks.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}