import java.util.Scanner;
import java.util.ArrayList;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" for done, " " for not done
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

public class Astra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Astra!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim(); // Read and trim input

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                if (tasks.isEmpty()) {
                    System.out.println(" No tasks added yet.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(index));
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println(" Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(index));
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println(" Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid number.");
                }
            } else {
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
