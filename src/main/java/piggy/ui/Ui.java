package piggy.ui;

import piggy.util.Constants;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        printSeparator();
        System.out.println(Constants.PIG_INTRO);
        printSeparator();
    }

    public String readCommand() {
        System.out.println("Squeal! What should we do next? ");
        return new Scanner(System.in).nextLine().trim();
    }

    public void showLine() {
        printSeparator();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        showError("Oink! Error loading tasks from file.");
    }

    private void printSeparator() {
        System.out.println(Constants.SEPARATOR);
        System.out.flush();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}