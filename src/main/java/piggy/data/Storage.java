package piggy.data;

import piggy.task.Task;
import piggy.exceptions.PiggyException;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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