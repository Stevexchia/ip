package piggy.data;

import java.io.*;
import java.util.ArrayList;

import piggy.task.Task;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;
import piggy.task.ToDo;
import piggy.task.Deadline;
import piggy.task.Event;

public class TaskList {
    private final ArrayList<Task> tasks;
    private static final String FILE_PATH = "PiggyOutput.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(Task task) throws PiggyException {
        if (task.getDescription().isEmpty()) {
            throw new PiggyException("OOPS!!! The description of a todo cannot be empty, piggy snout!");
        }
        tasks.add(task);
        saveTasks();

        printSeparator();
        System.out.println(" Oink! This task is now in my snout:");
        System.out.println("   " + task);
        System.out.println(" Snort! You’ve got " + tasks.size() + " tasks in your pen.");
        printSeparator();
    }

    public void listTasks() {
        printSeparator();
        if (tasks.isEmpty()) {
            System.out.println(" Oink... No tasks in the mud here!");
        } else {
            System.out.println(" Here’s what’s on my mind, oink oink!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        printSeparator();
    }

    public void markTask(int index, boolean done) {
        try {
            if (!isValidIndex(index)) {
                throw new PiggyException("Oops, that task number doesn’t exist in my mud!");
            }

            Task task = tasks.get(index);
            printSeparator();
            if (done) {
                task.markAsDone();
                System.out.println(" Oink! I’ve finished munching on this task:");
            } else {
                task.markAsNotDone();
                System.out.println(" Uh-oh, not yet! I’ll leave this task for later:");
            }
            saveTasks();
            System.out.println("   " + task);
            printSeparator();
        } catch (PiggyException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public void deleteTask(int index) {
        try {
            if (!isValidIndex(index)) {
                throw new PiggyException("Oops, that task number doesn’t exist in my mud!");
            }

            Task removedTask = tasks.get(index);
            tasks.remove(index);

            printSeparator();
            System.out.println(" Snort! This task is forgotten in the haystack:");
            System.out.println("   " + removedTask);
            System.out.println(" Snort! You’ve got " + tasks.size() + " tasks in your pen.");
            printSeparator();
        } catch (PiggyException e) {
            printErrorMessage(e.getMessage());
        }
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split(" \\| ");
                if (taskDetails.length > 1) {
                    boolean isDone = taskDetails[1].equals("1");  // Determine if the task is done (1 means done, 0 means not done)
                    switch (taskDetails[0]) {
                        case "T":
                            ToDo toDo = new ToDo(taskDetails[2]);
                            if (isDone) toDo.markAsDone();
                            tasks.add(toDo);
                            break;
                        case "D":
                            Deadline deadline = new Deadline(taskDetails[2], taskDetails[3]);
                            if (isDone) deadline.markAsDone();
                            tasks.add(deadline);
                            break;
                        case "E":
                            Event event = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                            if (isDone) event.markAsDone();
                            tasks.add(event);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No existing tasks file found. Starting fresh!");
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    private void printSeparator() {
        System.out.println(Constants.SEPARATOR);
    }

    private void printErrorMessage(String message) {
        System.out.println(Constants.SEPARATOR);
        System.out.println(" " + message);
        System.out.println(Constants.SEPARATOR);
    }

}
