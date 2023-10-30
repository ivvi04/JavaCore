package ru.lakeevda.lesson3.homework.menu;

import ru.lakeevda.lesson3.homework.services.DepartmentService;

import java.util.InputMismatchException;

public class DepartmentMenu extends AbstractMenu {
    static DepartmentService departmentService = new DepartmentService();

    public static void printMenu() {
        AbstractMenu.printMenu();
        System.out.println("   1   | Вывести список отделов");
        System.out.println("   2   | Вывести отдел по id");
        System.out.println("   3   | Добавить отдел");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            switch (readMenu()) {
                case "1" -> System.out.println(departmentService.getDepartmentRepository());
                case "2" -> {
                    int departmentId;
                    while (true) {
                        try {
                            departmentId = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                    System.out.println(departmentService.getDepartmentById(departmentId));
                }
                case "3" -> departmentService.addDepartmentConsole();
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }
    }
}
