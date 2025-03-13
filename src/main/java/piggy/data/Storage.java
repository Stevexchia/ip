package piggy.data;

import piggy.task.Task;
import piggy.exceptions.PiggyException;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws PiggyException If there is an error loading tasks.
     */
    public ArrayList<Task> load() throws PiggyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create the file if it doesn't exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile();
            } catch (IOException e) {
                throw new PiggyException("Error creating tasks file.");
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromFileString(line));
            }
        } catch (IOException e) {
            throw new PiggyException("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws PiggyException If there is an error saving tasks.
     */
    public void save(ArrayList<Task> tasks) throws PiggyException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new PiggyException("Error saving tasks to file.");
        }
    }
}