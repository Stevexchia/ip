package piggy.data;

import piggy.task.Task;
import piggy.exceptions.PiggyException;

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
            throw new PiggyException("OOPS!!! The description of a todo cannot be empty, piggy snout!");
        }
        tasks.add(task);
    }

    public void deleteTask(int index) throws PiggyException {
        if (!isValidIndex(index)) {
            throw new PiggyException("Oops, that task number doesn’t exist in my mud!");
        }
        Task removedTask = tasks.remove(index);
    }

    public void markTask(int index, boolean isDone) throws PiggyException {
        if (!isValidIndex(index)) {
            throw new PiggyException("Oops, that task number doesn’t exist in my mud!");
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
            System.out.println(" Oink... No tasks in the mud here!");
        } else {
            System.out.println(" Here’s what’s on my mind, oink oink!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}