package ru.lakeevda.lesson3.homework.services;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.exceptions.*;
import ru.lakeevda.lesson3.homework.planner.TaskPlanner;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;
import ru.lakeevda.lesson3.homework.repository.FreeTaskRepository;

import java.util.List;

public class ManagerService {
    public void increaseSalaryByManagerByEmployee(Employee manager, Employee employee, double amount) throws Exception {
        if (Checker.employeeIsNotNull(manager) && Checker.employeeIsNotNull(employee)) {
            if (amount <= 0) throw new EmployeeException("The amount is less than or equal to zero");
            if (!manager.getSkill().equals(Skill.MANAGER))
                throw new EmployeeException("Only a manager can increase salary");
            if (manager.equals(employee)) throw new EmployeeException("A manager cannot increase his own salary");
            if (!manager.getDepartment().equals(employee.getDepartment()))
                throw new EmployeeException("A manager can increase salary only in his own department");
            employee.setSalary(employee.getSalary() + amount);
        }
    }

    public void increaseSalaryByManagerAllEmployees(Employee manager, double amount) throws Exception {
        if (Checker.employeeIsNotNull(manager)) {
            if (amount <= 0) throw new EmployeeException("The amount is less than or equal to zero");
            List<Employee> employeeList = EmployeeRepository.getEmployeeRepository().stream()
                    .filter(x -> x.getDepartment().equals(manager.getDepartment()))
                    .filter(x -> !x.equals(manager))
                    .toList();
            for (Employee employee : employeeList) {
                increaseSalaryByManagerByEmployee(manager, employee, amount);
            }
        }
    }

    public void startFreeTaskByManager(Employee manager) throws Exception {
        if (Checker.employeeIsNotNull(manager)) {
            List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
            if (!taskList.isEmpty()) startTaskByManager(manager, taskList.get(0));
        }
    }

    public void startTaskByManager(Employee manager, Task task) throws Exception {
        if (Checker.employeeIsNotNull(manager) && Checker.taskIsNotNull(task)) {
            Employee employee = TaskPlanner.findEmployee(task);
            if (employee != null) startTaskByManager(manager, task, employee);
        }
    }

    public void startTaskByManager(Employee manager, Task task, Employee employee) throws Exception {
        if (Checker.employeeIsNotNull(manager) && Checker.taskIsNotNull(task) && Checker.employeeIsNotNull(employee)) {
            List<Assignment> assignmentList = AssignmentRepository.getAssignmentRepository().stream()
                    .filter(x -> x.getTask() == task)
                    .toList();
            if (!assignmentList.isEmpty()) throw new TaskException("This task has already been assigned");
            assignmentList = AssignmentRepository.getAssignmentRepository().stream()
                    .filter(x -> x.getEmployee() == employee)
                    .filter(x -> x.getStatus().equals(Status.IN_PROGRESS))
                    .filter(x -> x.getTask().getPriority().getCode() >= task.getPriority().getCode())
                    .toList();
            if (!assignmentList.isEmpty())
                throw new EmployeeException("This employee already has a assignment with the same priority or higher");
            Assignment assignment = TaskPlanner.createAssigment(employee, task);
            EmployeeService employeeService = new EmployeeService();
            employeeService.startAssignmentByEmployee(manager, assignment);
        }
    }
}
