package piggy.data;

import piggy.task.Deadline;
import piggy.task.Event;
import piggy.task.Task;
import piggy.exceptions.PiggyException;
import piggy.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of tasks. This class provides methods to add, delete, mark, and list tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     * @throws PiggyException If the task description is empty.
     */
    public void addTask(Task task) throws PiggyException {
        if (task.getDescription().isEmpty()) {
            throw new PiggyException(Constants.INVALID_FORMAT_TODO_MESSAGE);
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The index of the task to delete.
     * @throws PiggyException If the index is invalid.
     */
    public void deleteTask(int index) throws PiggyException {
        if (!isValidIndex(index)) {
            throw new PiggyException(Constants.INVALID_TASK_NUMBER_MESSAGE);
        }
        Task removedTask = tasks.remove(index);
    }

    /**
     * Marks a task as done or not done.
     *
     * @param index The index of the task to mark.
     * @param isDone True to mark the task as done, false to mark it as not done.
     * @throws PiggyException If the index is invalid.
     */
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

    /**
     * Lists all tasks in the TaskList.
     */
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


    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     * @throws PiggyException If the index is invalid.
     */
    public Task get(int index) throws PiggyException {
        if (!isValidIndex(index)) {
            throw new PiggyException(Constants.INVALID_TASK_NUMBER_MESSAGE);
        }
        return tasks.get(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
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
        }
    }

    /**
     * Filters and displays tasks that occur on the specified date.
     * This includes deadlines due on the date and events that start or end on the date.
     *
     * @param date The date to filter tasks by.
     */
    public void filterTasksByDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        boolean hasTasks = false;
        StringBuilder tasksOnDate = new StringBuilder();

        // Check each task for a match
        for (Task task : tasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getBy().toLocalDate().equals(date)) {
                    if (!hasTasks) {
                        tasksOnDate.append("ZZZ.. You have these tasks on ").append(date.format(formatter)).append(":\n");
                    }
                    tasksOnDate.append("  ").append(task).append("\n");
                    hasTasks = true; // Set flag to true if a task is found
                }
            } else if (task instanceof Event event) {
                if (event.getFrom().toLocalDate().equals(date) || event.getTo().toLocalDate().equals(date)) {
                    if (!hasTasks) {
                        tasksOnDate.append("ZZZ.. You have these tasks on ").append(date.format(formatter)).append(":\n");
                    }
                    tasksOnDate.append("  ").append(task).append("\n");
                    hasTasks = true; // Set flag to true if a task is found
                }
            }
        }

        // Print the result
        if (hasTasks) {
            System.out.println(tasksOnDate.toString().trim()); // Print all matching tasks
        } else {
            System.out.println(" Oink... Nothing to do on this day, let's chill!");
        }
    }
}