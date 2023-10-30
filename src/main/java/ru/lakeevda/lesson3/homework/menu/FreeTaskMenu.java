package ru.lakeevda.lesson3.homework.menu;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.exceptions.AssignmentException;
import ru.lakeevda.lesson3.homework.exceptions.EmployeeException;
import ru.lakeevda.lesson3.homework.planner.TaskPlanner;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.services.EmployeeService;
import ru.lakeevda.lesson3.homework.services.TaskService;

import java.util.InputMismatchException;

public class FreeTaskMenu extends AbstractMenu {
    static TaskService taskService = new TaskService();

    public static void printMenu() {
        printSeparator();
        System.out.println("   1   | Вывести список свободных задач");
        System.out.println("   2   | Вывести задачу по id");
        System.out.println("   3   | Добавить задачу");
        System.out.println("   4   | Назначить задачу по id задачи");
        System.out.println("   5   | Назначить первую свободную задачу");
        System.out.println("   6   | Назначить все задачи");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            switch (readMenu()) {
                case "1" -> System.out.println(taskService.getFreeTaskRepository());
                case "2" -> {
                    int taskId;
                    while (true) {
                        try {
                            System.out.print("Введите id задачи: ");
                            taskId = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                    System.out.println(taskService.getTaskById(taskId));
                }
                case "3" -> taskService.addTaskConsole();
                case "4" -> {
                    int taskId;
                    while (true) {
                        try {
                            System.out.print("Введите id задачи: ");
                            taskId = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                    Task task = taskService.getTaskById(taskId);
                    Assignment assignment = null;
                    try {
                        assignment = TaskPlanner.planTask(task);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (assignment == null) System.out.println("Не удалось назначить задачу");
                }
                case "5" -> {
                    Task task = taskService.getFirstTask();
                    Assignment assignment = null;
                    System.out.println("Планируемая задача: " + task);
                    try {
                        assignment = TaskPlanner.planTask(task);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (assignment == null) System.out.println("Не удалось назначить задачу");
                }
                case "6" -> {
                    try {
                        TaskPlanner.planAllTask();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }
    }
}
