package ru.lakeevda.lesson3.homework.menu;

public class MainMenu extends AbstractMenu {

    public static void printMenu() {
        AbstractMenu.printMenu();
        System.out.println("   1   | Меню по отделам");
        System.out.println("   2   | Меню по сотрудникам");
        System.out.println("   3   | Меню по задачам");
        System.out.println("   4   | Меню по назначенным задачам");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            switch (readMenu()) {
                case "1":
                    DepartmentMenu.viewMenu();
                    break;
                case "2":
                    EmployeeMenu.viewMenu();
                    break;
                case "3":
                    FreeTaskMenu.viewMenu();
                    break;
                case "4":
                    AssignmentMenu.viewMenu();
                    break;
                case "0":
                    isRepeat = false;
                    break;
                default:
                    System.out.println("Введен неизвестный код!");
            }
        }
    }
}
