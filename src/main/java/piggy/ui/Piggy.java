package piggy.ui;

import piggy.data.Storage;
import piggy.data.TaskList;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;
import piggy.util.Parser;
import piggy.commands.Command;

public class Piggy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
        System.out.println(Constants.BYE_MESSAGE);
    }

    public static void main(String[] args) {
        new Piggy("data/tasks.txt").run();
    }
}