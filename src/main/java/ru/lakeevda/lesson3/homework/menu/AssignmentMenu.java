package ru.lakeevda.lesson3.homework.menu;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.planner.TaskPlanner;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.services.TaskService;

import java.util.InputMismatchException;

public class AssignmentMenu extends AbstractMenu {

    public static void printMenu() {
        AbstractMenu.printMenu();
        System.out.println("   1   | Вывести список назначенных заданий");
        System.out.println("   2   | Вывести назначенное задание по id");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            switch (readMenu()) {
                case "1" -> System.out.println(AssignmentRepository.getAssignmentRepository());
                case "2" -> {
                    int assignmentId;
                    while (true) {
                        try {
                            System.out.print("Введите id назначенного задания: ");
                            assignmentId = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                    System.out.println(AssignmentRepository.getAssignmentById(assignmentId));
                }
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }
    }
}
