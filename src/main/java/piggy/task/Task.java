package piggy.task;

import piggy.exceptions.PiggyException;

/*
  Represents a generic task in the task manager.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return The string representation of the task in file format.
     */
    public abstract String toFileString();

    /**
     * Creates a Task object from a string representation in a file.
     *
     * @param fileString The string representation of the task from the file.
     * @return The Task object.
     * @throws PiggyException If the file string is invalid.
     */
    public static Task fromFileString(String fileString) throws PiggyException {
        String[] parts = fileString.split(" \\| ");
        if (parts.length < 3) {
            throw new PiggyException("Invalid task format in file.");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = switch (type) {
            case "T" -> new ToDo(description);
            case "D" -> {
                if (parts.length < 4) {
                    throw new PiggyException("Invalid deadline format in file.");
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime by = LocalDateTime.parse(parts[3], formatter);
                    yield new Deadline(description, by);
                } catch (DateTimeParseException e) {
                    throw new PiggyException("Invalid date/time format in file for deadline.");
                }
            }
            case "E" -> {
                if (parts.length < 5) {
                    throw new PiggyException("Invalid event format in file.");
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                    LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
                    yield new Event(description, from, to);
                } catch (DateTimeParseException e) {
                    throw new PiggyException("Invalid date/time format in file for event.");
                }
            }
            default -> throw new PiggyException("Unknown task type in file.");
        };

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}