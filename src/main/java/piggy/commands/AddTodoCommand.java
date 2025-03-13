package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.task.ToDo;
import piggy.util.Constants;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task.
     *
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui to interact with the user.
     * @param storage The Storage to save the tasks.
     * @throws PiggyException If the task description is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());

        ui.showMessage(Constants.TASK_ADDED_MESSAGE);
        ui.showMessage("  " + todo);
        ui.showMessage(String.format(Constants.TASKS_COUNT_MESSAGE, tasks.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}