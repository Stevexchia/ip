package piggy.ui;

import piggy.data.Storage;
import piggy.data.TaskList;
import piggy.exceptions.PiggyException;
import piggy.util.Parser;
import piggy.commands.Command;

/**
 * The main class for the Piggy task manager application.
 * This class initializes the application and runs the command loop.
 */
public class Piggy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Piggy instance with the specified file path for storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Piggy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PiggyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Piggy application. Displays the welcome message and processes user commands
     * until the exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // Show separator before command execution
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PiggyException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine(); // Show separator after command execution
        }
    }

    public static void main(String[] args) {
        new Piggy("data/tasks.txt").run();
    }
}