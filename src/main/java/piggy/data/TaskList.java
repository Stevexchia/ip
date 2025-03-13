package piggy.data;

import piggy.task.Deadline;
import piggy.task.Event;
import piggy.task.Task;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) throws PiggyException {
        if (task.getDescription().isEmpty()) {
            throw new PiggyException(Constants.INVALID_FORMAT_TODO_MESSAGE);
        }
        tasks.add(task);
    }

    public void deleteTask(int index) throws PiggyException {
        if (!isValidIndex(index)) {
            throw new PiggyException(Constants.INVALID_TASK_NUMBER_MESSAGE);
        }
        Task removedTask = tasks.remove(index);
    }

    public void markTask(int index, boolean isDone) throws PiggyException {
        if (!isValidIndex(index)) {
            throw new PiggyException(Constants.INVALID_TASK_NUMBER_MESSAGE);
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(Constants.NO_TASKS_MESSAGE);
        } else {
            System.out.println(Constants.TASKS_LIST_HEADER);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) throws PiggyException {
        if (!isValidIndex(index)) {
            throw new PiggyException(Constants.INVALID_TASK_NUMBER_MESSAGE);
        }
        return tasks.get(index);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    public void findTasksByKeyword(String keyword) {
        boolean hasMatches = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                // Print the header only if this is the first match
                if (!hasMatches) {
                    System.out.println(Constants.FIND_MESSAGE);
                }
                System.out.println(" " + (i + 1) + "." + task);
                hasMatches = true;
            }
        }

        if (!hasMatches) {
            System.out.println(Constants.INVALID_FIND_MESSAGE + keyword + "'!");

    public void filterTasksByDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        boolean hasTasks = false;

        for (Task task : tasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getBy().toLocalDate().equals(date)) {
                    System.out.println("ZZZ.. You have these tasks on " + date.format(formatter) + ":");
                    System.out.println("  " + task);
                    hasTasks = true; // Set flag to true if a task is found
                }
            } else if (task instanceof Event event) {
                if (event.getFrom().toLocalDate().equals(date) || event.getTo().toLocalDate().equals(date)) {
                    System.out.println("ZZZ.. You have these tasks on " + date.format(formatter) + ":");
                    System.out.println("  " + task);
                    hasTasks = true; // Set flag to true if a task is found
                }
            }
        }

        if (!hasTasks) {
            System.out.println(" Oink... Nothing to do on this day, let's chill!");
        }
    }
}