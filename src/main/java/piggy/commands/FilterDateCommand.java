package piggy.commands;

import piggy.data.TaskList;
import piggy.ui.Ui;
import piggy.data.Storage;
import piggy.exceptions.PiggyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FilterDateCommand extends Command {
    private final LocalDate date;

    public FilterDateCommand(String arguments) throws PiggyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.date = LocalDate.parse(arguments.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new PiggyException("Oink! Invalid date format! Use: filter <yyyy-MM-dd>");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PiggyException {
        tasks.filterTasksByDate(date);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}