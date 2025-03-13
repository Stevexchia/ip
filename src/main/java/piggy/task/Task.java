package piggy.task;

import piggy.exceptions.PiggyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toFileString();

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