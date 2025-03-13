package piggy.util;

import piggy.commands.*;
import piggy.exceptions.PiggyException;

/**
 * Parses user input into commands that the application can execute.
 */
public class Parser {
    /**
     * Parses the full command entered by the user and returns the corresponding command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return The command object corresponding to the user's input.
     * @throws PiggyException If the command is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws PiggyException {
        String[] parts = fullCommand.split(Constants.SPACE, 2);
        String commandWord = parts[0].trim();
        String arguments = parts.length > Constants.MAX_ARGUMENTS_LENGTH ? parts[1].trim() : "";

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
            case Constants.FIND_COMMAND:
                return new FindCommand(arguments);
            case Constants.FINDDATE_COMMAND:
                return new FindByDateCommand(arguments);
            default:
                throw new PiggyException(Constants.UNKNOWN_COMMAND_MESSAGE);
        }
    }
}