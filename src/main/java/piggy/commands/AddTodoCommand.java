package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;
import piggy.task.ToDo;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());

        ui.showMessage("Oink! This task is now in my snout:");
        ui.showMessage("  " + todo);
        ui.showMessage("Snort! You’ve got " + tasks.size() + " tasks in your pen.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}