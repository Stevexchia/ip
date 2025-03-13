package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.task.Event;
import piggy.util.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public AddEventCommand(String arguments) throws PiggyException {
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new PiggyException(Constants.INVALID_FORMAT_EVENT_MESSAGE);
        }
        this.description = parts[0].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME);
            this.from = LocalDateTime.parse(parts[1].trim(), formatter);
            this.to = LocalDateTime.parse(parts[2].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new PiggyException(Constants.INVALID_DATE_TIME);
        }
    }

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