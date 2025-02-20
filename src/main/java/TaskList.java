import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws PiggyException {
        if (task.getDescription().isEmpty()) {
            throw new PiggyException("OOPS!!! The description of a todo cannot be empty, piggy snout!");
        }

        tasks.add(task);
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
            System.out.println("   " + task);
            printSeparator();
        } catch (PiggyException e) {
            printErrorMessage(e.getMessage());
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
