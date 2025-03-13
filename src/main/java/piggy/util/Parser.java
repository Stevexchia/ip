package piggy.util;

import piggy.commands.*;
import piggy.exceptions.PiggyException;

public class Parser {
    public static Command parse(String fullCommand) throws PiggyException {
        String[] parts = fullCommand.split(" ", 2); // Split into command and arguments
        String commandWord = parts[0].trim(); // Get the command word
        String arguments = parts.length > 1 ? parts[1].trim() : ""; // Get the arguments (if any)

        switch (commandWord) {
            case Constants.TODO_COMMAND:
                return new AddTodoCommand(arguments);
            case Constants.DEADLINE_COMMAND:
                return new AddDeadlineCommand(arguments);
            case Constants.EVENT_COMMAND:
                return new AddEventCommand(arguments);
            case Constants.MARK_COMMAND:
                return new MarkCommand(arguments, true);
            case Constants.UNMARK_COMMAND:
                return new MarkCommand(arguments, false);
            case Constants.DELETE_COMMAND:
                return new DeleteCommand(arguments);
            case Constants.LIST_COMMAND:
                return new ListCommand();
            case Constants.BYE_COMMAND:
                return new ExitCommand();
            default:
                throw new PiggyException(Constants.UNKNOWN_COMMAND_MESSAGE);
        }
    }
}