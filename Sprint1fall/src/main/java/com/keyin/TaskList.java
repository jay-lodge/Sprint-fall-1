package com.keyin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void saveTasksToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Task task : tasks) {
                writer.println(task.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasksFromFile(String fileName) {
        tasks.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String description = parts[0];
                    boolean completed = Boolean.parseBoolean(parts[1]);
                    Task task = new Task(description);
                    if (completed) {
                        task.markCompleted();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // The file doesn't exist so no can tasks can load not an error smile .
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
