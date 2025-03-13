package piggy.ui;

import piggy.util.Constants;

import java.util.Scanner;

/**
 * Handles user interface interactions, such as displaying messages and reading user input.
 */
public class Ui {
    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        printSeparator();
        System.out.println(Constants.PIG_INTRO);
        printSeparator();
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        System.out.println("Squeal! What should we do next? ");
        return new Scanner(System.in).nextLine().trim();
    }

    /**
     * Displays a separator line in the UI.
     */
    public void showLine() {
        printSeparator();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded from the file.
     */
    public void showLoadingError() {
        showError("Oink! Error loading tasks from file.");
    }

    /**
     * Prints a separator line to the console.
     */
    private void printSeparator() {
        System.out.println(Constants.SEPARATOR);
        System.out.flush();
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}