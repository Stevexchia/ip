package piggy.ui;

import java.util.Scanner;

import piggy.util.Constants;
import piggy.task.ToDo;
import piggy.task.Deadline;
import piggy.task.Event;
import piggy.data.TaskList;
import piggy.exceptions.PiggyException;

public class Piggy {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        printIntro();
        runCommandLoop();
        scanner.close();
    }

    private static void printIntro() {
        printSeparator();
        System.out.println(Constants.PIG_INTRO);
        printSeparator();
    }

    private static void runCommandLoop() {
        while (true) {
            System.out.println("Squeal! What should we do next? ");
            String userInput = scanner.nextLine().trim();
            processCommand(userInput);
        }
    }

    private static void processCommand(String userInput) {
        try {
            // Extract the core command from user input (e.g., "mark", "todo", "bye")
            String coreCommand = getCoreCommand(userInput);
            switch (coreCommand) {
                case Constants.BYE_COMMAND:
                    handleGoodbye();
                    break;
                case Constants.LIST_COMMAND:
                    taskList.listTasks();
                    break;
                case Constants.MARK_COMMAND:
                    handleMarkCommand(userInput);
                    break;
                case Constants.UNMARK_COMMAND:
                    handleUnmarkCommand(userInput);
                    break;
                case Constants.TODO_COMMAND:
                    handleTodoCommand(userInput);
                    break;
                case Constants.DEADLINE_COMMAND:
                    handleDeadlineCommand(userInput);
                    break;
                case Constants.EVENT_COMMAND:
                    handleEventCommand(userInput);
                    break;
                default:
                    printUnknownCommand();
            }
        } catch (Exception e) {
            printErrorMessage("Oink! Something went wrong in the mud! Please try again!");
            printSeparator();
        }
    }

    private static String getCoreCommand(String userInput) {
        if (userInput.startsWith(Constants.MARK_COMMAND)) {
            return Constants.MARK_COMMAND;
        } else if (userInput.startsWith(Constants.UNMARK_COMMAND)) {
            return Constants.UNMARK_COMMAND;
        } else if (userInput.startsWith(Constants.TODO_COMMAND)) {
            return Constants.TODO_COMMAND;
        } else if (userInput.startsWith(Constants.DEADLINE_COMMAND)) {
            return Constants.DEADLINE_COMMAND;
        } else if (userInput.startsWith(Constants.EVENT_COMMAND)) {
            return Constants.EVENT_COMMAND;
        } else if (userInput.equals(Constants.BYE_COMMAND)) {
            return Constants.BYE_COMMAND;
        } else if (userInput.equals(Constants.LIST_COMMAND)) {
            return Constants.LIST_COMMAND;
        } else {
            return "unknown";
        }
    }

    private static void handleGoodbye() {
        printSeparator();
        System.out.println("Oink, oink! I'm going for a nap in the mud. See you soon! \uD83D\uDC16");
        printSeparator();
        System.exit(0);
    }

    private static void handleMarkCommand(String userInput) {
        try {
            int taskNumberToMark = Integer.parseInt(userInput.substring(5).trim()) - 1;
            taskList.markTask(taskNumberToMark, true);
        } catch (NumberFormatException e) {
            System.out.println("Oink! Please enter a valid task number to mark.");
            printSeparator();
        }
    }

    private static void handleUnmarkCommand(String userInput) {
        try {
            int taskNumberToUnmark = Integer.parseInt(userInput.substring(7).trim()) - 1;
            taskList.markTask(taskNumberToUnmark, false);
        } catch (NumberFormatException e) {
            printSeparator();
            System.out.println("Oink! Please enter a valid task number to mark.");
            printSeparator();
        }
    }

    private static void handleTodoCommand(String userInput) throws PiggyException {
        taskList.addTask(new ToDo(userInput.substring(5).trim()));
    }

    private static void handleDeadlineCommand(String userInput) throws PiggyException {
        String[] parts = userInput.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            printSeparator();
            System.out.println("Oink! Invalid format! Use: deadline <description> /by <time>");
            printSeparator();
        } else {
            taskList.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
        }
    }

    private static void handleEventCommand(String userInput) throws PiggyException {
        String[] parts = userInput.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) {
            printSeparator();
            System.out.println("Oink! Invalid format! Use: event <description> /from <start> /to <end>");
            printSeparator();
        } else {
            taskList.addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        }
    }

    private static void printSeparator() {
        System.out.println(Constants.SEPARATOR);
    }

    private static void printErrorMessage(String message) {
        printSeparator();
        System.out.println(message);
        printSeparator();
    }

    private static void printUnknownCommand() {
        printErrorMessage("Oink, oink! Unknown command! Please try again, or I'll start snorting!");
    }
}
