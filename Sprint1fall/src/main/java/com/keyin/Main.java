package com.keyin;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);

        TaskList taskList = new TaskList();

        taskList.loadTasksFromFile(user.getName() + "_tasks.txt");

        while (true) {
            System.out.println("\nTask Management Menu");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Save and Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String taskDescription = scanner.nextLine();
                    Task task = new Task(taskDescription);
                    taskList.addTask(task);
                    System.out.println("Task added!");
                    break;
                case 2:
                    System.out.println("Tasks:");
                    List<Task> tasks = taskList.getTasks();
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        String status = t.isCompleted() ? "Completed" : "Not Completed";
                        System.out.println(i + 1 + ". " + t.getDescription() + " (" + status + ")");
                    }
                    break;
                case 3:
                    System.out.print("Enter the task number to mark as completed: ");
                    int taskNumber = scanner.nextInt();
                    Task taskToMark = taskList.getTasks().get(taskNumber - 1);
                    taskToMark.markCompleted();
                    System.out.println("Task marked as completed");
                    break;
                case 4:
                    System.out.print("Enter the task number to delete: ");
                    int taskToDelete = scanner.nextInt();
                    Task taskToDeleteObj = taskList.getTasks().get(taskToDelete - 1);
                    taskList.removeTask(taskToDeleteObj);
                    System.out.println("Task deleted");
                    break;
                case 5:
                    taskList.saveTasksToFile(user.getName() + "_tasks.txt");
                    System.out.println("Tasks saved. Goodbye " + user.getName());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
