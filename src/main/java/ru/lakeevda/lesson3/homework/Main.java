package ru.lakeevda.lesson3.homework;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.enums.Priority;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.exceptions.AssignmentException;
import ru.lakeevda.lesson3.homework.exceptions.EmployeeException;
import ru.lakeevda.lesson3.homework.planner.TaskPlanner;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.repository.DepartmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;
import ru.lakeevda.lesson3.homework.repository.FreeTaskRepository;
import ru.lakeevda.lesson3.homework.services.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Department department;
    static Employee engineer1;
    static Employee engineer2;
    static Employee manager;
    static Task taskLow1;
    static Task taskLow2;
    static Task taskMiddle1;
    static Task taskMiddle2;
    static Task taskMiddle3;
    static Task taskMiddle4;
    static Task taskHigh1;
    static Task taskHigh2;

    public static void main(String[] args) {
        try {
            readData();
            viewMainMenu();
            writeData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void readData() throws Exception {
        FileService writeReadService = new FileService();
        writeReadService.FileReader();
    }

    public static void writeData() throws Exception {
        FileService writeReadService = new FileService();
        writeReadService.FileWriter();
    }

    public static String readMenu() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static void printSeparator() {
        System.out.println("---------------------------------------------------");
    }


    public static void printMainMenu() {
        printSeparator();
        System.out.println("   1   | Меню по отделам");
        System.out.println("   2   | Меню по сотрудникам");
        System.out.println("   3   | Меню по задачам");
        System.out.println("   4   | Меню по назначенным задачам");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMainMenu() {

        boolean isRepeat = true;
        while (isRepeat) {
            printMainMenu();
            switch (readMenu()) {
                case "1":
                    viewDepartmentMenu();
                    break;
                case "2":
                    viewEmployeeMenu();
                    break;
                case "3":
                    viewFreeTaskMenu();
                    break;
                case "4":
                    viewAssignmentMenu();
                    break;
                case "0":
                    isRepeat = false;
                    break;
                default:
                    System.out.println("Введен неизвестный код!");
            }
        }
    }

    public static void printDepartmentMenu() {
        printSeparator();
        System.out.println("   1   | Вывести список отделов");
        System.out.println("   2   | Вывести отдел по id");
        System.out.println("   3   | Добавить отдел");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewDepartmentMenu() {
        boolean isRepeat = true;
        DepartmentService departmentService = new DepartmentService();
        while (isRepeat) {
            printDepartmentMenu();
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

    public static void printEmployeeMenu() {
        printSeparator();
        System.out.println("   1   | Вывести список сотрудников");
        System.out.println("   2   | Вывести сотрудника по id");
        System.out.println("   3   | Добавить сотрудника");
        System.out.println("   4   | Вывести назначенные задания по id сотрудника");
        System.out.println("   5   | Начать назначенное задание по id сотрудника и id задания");
        System.out.println("   6   | Закончить назначенное задание по id сотрудника и id задания");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewEmployeeMenu() {
        boolean isRepeat = true;
        EmployeeService employeeService = new EmployeeService();
        while (isRepeat) {
            printEmployeeMenu();
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
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }

    }


    public static void printFreeTaskMenu() {
        printSeparator();
        System.out.println("   1   | Вывести список задач");
        System.out.println("   2   | Вывести задачу по id");
        System.out.println("   3   | Добавить задачу");
        System.out.println("   4   | Назначить задачу по id задачи");
        System.out.println("   5   | Назначить первую свободную задачу");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewFreeTaskMenu() {
        boolean isRepeat = true;
        TaskService taskService = new TaskService();
        while (isRepeat) {
            printFreeTaskMenu();
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
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }
    }

    public static void printAssignmentMenu() {
        printSeparator();
        System.out.println("   1   | Вывести список назначенных заданий");
        System.out.println("   2   | Вывести назначенное задание по id");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewAssignmentMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printAssignmentMenu();
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

    public static void createEmployees() {
        engineer1 = new Employee("Смирнов"
                , "Иван"
                , LocalDate.of(1965, 2, 26)
                , 100
                , department
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(engineer1);
        engineer2 = new Employee("Иванов"
                , "Дмитрий"
                , LocalDate.of(1978, 8, 14)
                , 200
                , department
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(engineer2);
        manager = new Employee("Соболев"
                , "Сергей"
                , LocalDate.of(1989, 5, 10)
                , 300
                , department
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(manager);
    }

    public static void createTasks() throws Exception {

        taskLow1 = new Task("НизкаяЗадача1"
                , 1
                , LocalDate.of(2023, 10, 16)
                , Skill.ENGINEER
                , 10);
        FreeTaskRepository.addTask(taskLow1);
        taskLow2 = new Task("НизкаяЗадача2"
                , 2
                , LocalDate.of(2023, 10, 15)
                , Skill.ENGINEER
                , 20);
        FreeTaskRepository.addTask(taskLow2);
        taskMiddle1 = new Task("СредняяЗадача1"
                , 2
                , LocalDate.of(2023, 10, 17)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 5);
        FreeTaskRepository.addTask(taskMiddle1);
        taskMiddle2 = new Task("СредняяЗадача2"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 10);
        FreeTaskRepository.addTask(taskMiddle2);
        taskMiddle3 = new Task("СредняяЗадача3"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 9);
        FreeTaskRepository.addTask(taskMiddle3);
        taskMiddle4 = new Task("СредняяЗадача4"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 5);
        FreeTaskRepository.addTask(taskMiddle4);
        taskHigh1 = new Task("ВысокаяЗадача1"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.HIGH
                , Skill.ENGINEER
                , 6);
        FreeTaskRepository.addTask(taskHigh1);
        taskHigh2 = new Task("ВысокаяЗадача2"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.HIGH
                , Skill.ENGINEER
                , 10);
        FreeTaskRepository.addTask(taskHigh2);
    }
}
