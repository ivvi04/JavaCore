package ru.lakeevda.lesson3.homework;


import ru.lakeevda.lesson3.homework.entity.assigment.Assigment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.enums.Priority;
import ru.lakeevda.lesson3.homework.entity.person.*;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;
import ru.lakeevda.lesson3.homework.services.ManagerService;
import ru.lakeevda.lesson3.homework.planner.TaskPlanner;
import ru.lakeevda.lesson3.homework.services.EmployeeService;
import ru.lakeevda.lesson3.homework.entity.task.Task;

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
    static Task taskHigh1;
    static Task taskHigh2;

    public static void main(String[] args) {
        department = new Department("Департамент1");
        createEmployees();
        department.setManager(manager);
        createTasks();
        EmployeeService employeeService = new EmployeeService();

        Assigment assigmentTaskLow1 = TaskPlanner.planTask(taskLow1);
        employeeService.startTaskByEmployee(engineer1, assigmentTaskLow1);

        Assigment assigmentTaskLow2 = TaskPlanner.planTask(taskLow2);
        employeeService.startTaskByEmployee(engineer2, assigmentTaskLow2);

        Assigment assigmentTaskLow3 = TaskPlanner.planTask(taskMiddle1);
        employeeService.startTaskByEmployee(engineer1, assigmentTaskLow3);

        Assigment assigmentTaskLow4 = TaskPlanner.planTask(taskMiddle2);
        employeeService.startTaskByEmployee(engineer2, assigmentTaskLow4);

        Assigment assigmentTaskLow5 = TaskPlanner.planTask(taskHigh1);
        employeeService.startTaskByEmployee(manager, assigmentTaskLow5);

        employeeService.finishTaskByEmployee(engineer1, assigmentTaskLow5);

        System.out.println(employeeService.getAssigmentsByEmployee(engineer1));
        System.out.println(employeeService.getAssigmentsByEmployee(engineer2));
        System.out.println(employeeService.getAssigmentsByEmployee(manager));

        ManagerService managerService = new ManagerService();
        managerService.increaseSalaryByManagerAllEmployees(manager, 100);
        System.out.println(EmployeeRepository.getEmployees());
    }

    public static void createEmployees() {
        engineer1 = new Employee("Смирнов"
                , "Иван"
                , LocalDate.of(1965, 2, 26)
                , 100
                , department,
                Skill.ENGINEER);
        EmployeeRepository.addEmployee(engineer1);
        engineer2 = new Employee("Иванов"
                , "Дмитрий"
                , LocalDate.of(1978, 8, 14)
                , 200
                , department,
                Skill.ENGINEER);
        EmployeeRepository.addEmployee(engineer2);
        manager = new Manager("Соболев"
                , "Сергей"
                , LocalDate.of(1989, 5, 10)
                , 300
                , department,
                Skill.MANAGER);
        EmployeeRepository.addEmployee(manager);
    }

    public static void createTasks() {

        taskLow1 = new Task("НизкаяЗадача1"
                , 1,
                LocalDate.of(2023, 10, 16)
                , Skill.ENGINEER
                , 10);

        taskLow2 = new Task("НизкаяЗадача2"
                , 2,
                LocalDate.of(2023, 10, 15)
                , Skill.ENGINEER
                , 20);

        taskMiddle1 = new Task("СредняяЗадача1"
                , 2,
                LocalDate.of(2023, 10, 17)
                , Skill.ENGINEER
                , 5);
        taskMiddle1.setPriority(Priority.MIDDLE);

        taskMiddle2 = new Task("СредняяЗадача2"
                , 3,
                LocalDate.of(2023, 10, 16)
                , Skill.ENGINEER
                , 10);
        taskMiddle2.setPriority(Priority.MIDDLE);

        taskHigh1 = new Task("ВысокаяЗадача1"
                , 3,
                LocalDate.of(2023, 10, 16)
                , Skill.ENGINEER
                , 10);
        taskHigh1.setPriority(Priority.HIGH);
        taskHigh2 = new Task("ВысокаяЗадача2"
                , 3,
                LocalDate.of(2023, 10, 16)
                , Skill.ENGINEER
                , 10);
        taskHigh2.setPriority(Priority.HIGH);
    }
}
