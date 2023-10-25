package ru.lakeevda.lesson3.homework;


import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.enums.Priority;
import ru.lakeevda.lesson3.homework.entity.person.*;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.planner.TaskPlanner;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;
import ru.lakeevda.lesson3.homework.repository.FreeTaskRepository;
import ru.lakeevda.lesson3.homework.services.EmployeeService;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.services.ManagerService;

import java.time.LocalDate;

public class Main {
    static Department department;
    static Employee engineer1;
    static Employee engineer2;
    static Manager manager;
    static Task taskLow1;
    static Task taskLow2;
    static Task taskMiddle1;
    static Task taskMiddle2;
    static Task taskMiddle3;
    static Task taskMiddle4;
    static Task taskHigh1;
    static Task taskHigh2;

    public static void main(String[] args) {
        department = new Department("Департамент1");
        createEmployees();
        department.setManager(manager);
        try {
            createTasks();
            System.out.println("FreeTaskRepository: " + FreeTaskRepository.getRepository());
            EmployeeService employeeService = new EmployeeService();

            Assignment assigmentTaskLow1 = TaskPlanner.planTask(department, taskLow1);
            employeeService.startAssignmentByEmployee(engineer1, assigmentTaskLow1);

            Assignment assigmentTaskLow2 = TaskPlanner.planTask(department, taskLow2);
            employeeService.startAssignmentByEmployee(engineer2, assigmentTaskLow2);

//            Assignment assigmentTaskLow3 = TaskPlanner.planTask(department, taskMiddle1);
//            employeeService.startTaskByEmployee(engineer1, assigmentTaskLow3);
//
//            Assignment assigmentTaskLow4 = TaskPlanner.planTask(department, taskMiddle2);
//            employeeService.startTaskByEmployee(engineer2, assigmentTaskLow4);
//
//            employeeService.finishTaskByEmployee(engineer1, assigmentTaskLow3);
//
//            Assignment assigmentTaskLow5 = TaskPlanner.planTask(department, taskMiddle3);
//            employeeService.startTaskByEmployee(manager, assigmentTaskLow5);

            ManagerService managerService = new ManagerService();
            managerService.increaseSalaryByManagerAllEmployees(manager, 100);

            managerService.startFreeTaskByManager(manager);

            try {
                managerService.startTaskByManager(manager, taskMiddle2, engineer1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                managerService.startTaskByManager(manager, taskMiddle2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            employeeService.finishAssignmentByEmployee(engineer1, 2);

            System.out.println("Task by engineer1: " + employeeService.getAssigmentsByEmployee(engineer1));
            System.out.println("Task by engineer2: " + employeeService.getAssigmentsByEmployee(engineer2));
            System.out.println("Task by manager: " + employeeService.getAssigmentsByEmployee(manager));

            managerService.startFreeTaskByManager(manager);

            System.out.println("Task by engineer1: " + employeeService.getAssigmentsByEmployee(engineer1));
            System.out.println("Task by engineer2: " + employeeService.getAssigmentsByEmployee(engineer2));
            System.out.println("Task by manager: " + employeeService.getAssigmentsByEmployee(manager));

            System.out.println("FreeTaskRepository: " + FreeTaskRepository.getRepository());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
        manager = new Manager("Соболев"
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
        FreeTaskRepository.addTask(department, taskLow1);
        taskLow2 = new Task("НизкаяЗадача2"
                , 2
                , LocalDate.of(2023, 10, 15)
                , Skill.ENGINEER
                , 20);
        FreeTaskRepository.addTask(department, taskLow2);
        taskMiddle1 = new Task("СредняяЗадача1"
                , 2
                , LocalDate.of(2023, 10, 17)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 5);
        FreeTaskRepository.addTask(department, taskMiddle1);
        taskMiddle2 = new Task("СредняяЗадача2"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 10);
        FreeTaskRepository.addTask(department, taskMiddle2);
        taskMiddle3 = new Task("СредняяЗадача3"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 9);
        FreeTaskRepository.addTask(department, taskMiddle3);
        taskMiddle4 = new Task("СредняяЗадача4"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.MIDDLE
                , Skill.ENGINEER
                , 5);
        FreeTaskRepository.addTask(department, taskMiddle4);
        taskHigh1 = new Task("ВысокаяЗадача1"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.HIGH
                , Skill.ENGINEER
                , 6);
        FreeTaskRepository.addTask(department, taskHigh1);
        taskHigh2 = new Task("ВысокаяЗадача2"
                , 3
                , LocalDate.of(2023, 10, 16)
                , Priority.HIGH
                , Skill.ENGINEER
                , 10);
        FreeTaskRepository.addTask(department, taskHigh2);
    }
}
