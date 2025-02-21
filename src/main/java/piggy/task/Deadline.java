package piggy.task;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String byTime) {
        super(description);
        this.by = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
