package ru.lakeevda.lesson3.homework.menu;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.exceptions.AssignmentException;
import ru.lakeevda.lesson3.homework.exceptions.EmployeeException;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.services.DepartmentService;
import ru.lakeevda.lesson3.homework.services.EmployeeService;

import java.util.InputMismatchException;

public class EmployeeMenu extends AbstractMenu {
    static EmployeeService employeeService = new EmployeeService();

    public static void printMenu() {
        AbstractMenu.printMenu();
        System.out.println("   1   | Вывести список сотрудников");
        System.out.println("   2   | Вывести сотрудника по id");
        System.out.println("   3   | Добавить сотрудника");
        System.out.println("   4   | Вывести назначенные задания по id сотрудника");
        System.out.println("   5   | Начать назначенное задание по id сотрудника и id задания");
        System.out.println("   6   | Закончить назначенное задание по id сотрудника и id задания");
        System.out.println("   7   | Начать самое приоритетное задание по id сотрудника");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            switch (readMenu()) {
                case "1" -> System.out.println(employeeService.getEmployeeRepository());
                case "2" -> {
                    int employeeId;
                    while (true) {
                        try {
                            System.out.print("Введите id сотрудника: ");
                            employeeId = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                    System.out.println(employeeService.getEmployeeById(employeeId));
                }
                case "3" -> employeeService.addEmployeeConsole();
                case "4" -> {
                    while (true) {
                        try {
                            System.out.print("Введите id сотрудника: ");
                            int employeeId = Integer.parseInt(readMenu());
                            Employee employee = employeeService.getEmployeeById(employeeId);
                            if (employee == null) throw new EmployeeException();
                            System.out.println(employeeService.getAssignmentsByEmployee(employee));
                            break;
                        } catch (InputMismatchException | EmployeeException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                }
                case "5" -> {
                    while (true) {
                        try {
                            System.out.print("Введите id сотрудника: ");
                            int employeeId = Integer.parseInt(readMenu());
                            Employee employee = employeeService.getEmployeeById(employeeId);
                            if (employee == null) throw new EmployeeException();
                            System.out.print("Введите id задания: ");
                            int assigmentId = Integer.parseInt(readMenu());
                            Assignment assignment = AssignmentRepository.getAssignmentById(assigmentId);
                            if (assignment == null) throw new AssignmentException();
                            employeeService.startAssignmentByEmployee(employee, assignment);
                            break;
                        } catch (InputMismatchException | EmployeeException | AssignmentException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                }
                case "6" -> {
                    while (true) {
                        try {
                            System.out.print("Введите id сотрудника: ");
                            int employeeId = Integer.parseInt(readMenu());
                            Employee employee = employeeService.getEmployeeById(employeeId);
                            if (employee == null) throw new EmployeeException();
                            System.out.print("Введите id задания: ");
                            int assigmentId = Integer.parseInt(readMenu());
                            Assignment assignment = AssignmentRepository.getAssignmentById(assigmentId);
                            if (assignment == null) throw new AssignmentException();
                            employeeService.finishAssignmentByEmployee(employee, assignment);
                            break;
                        } catch (InputMismatchException | EmployeeException | AssignmentException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                }
                case "7" -> {
                    while (true) {
                        try {
                            System.out.print("Введите id сотрудника: ");
                            int employeeId = Integer.parseInt(readMenu());
                            Employee employee = employeeService.getEmployeeById(employeeId);
                            if (employee == null) throw new EmployeeException();
                            employeeService.startAssignmentPriorityByEmployee(employee);
                            break;
                        } catch (InputMismatchException | EmployeeException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }

                }
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }

    }
}
