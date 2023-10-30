package ru.lakeevda.lesson3.homework.planner;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.exceptions.Checker;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;
import ru.lakeevda.lesson3.homework.repository.FreeTaskRepository;

import java.util.List;

abstract public class TaskPlanner {
    public static Employee findEmployee(Task task) throws Exception {
        Employee employee = null;
        if (Checker.taskIsNotNull(task)) {
            List<Employee> employeeList = EmployeeRepository.getEmployeeRepository().stream()
                    .filter(x -> x.getSkill() == task.getSkill())
                    .filter(x -> !x.isWorking())
                    .filter(x -> {
                        /**
                         * Так же проверяем нет ли уже созданных заданий в статусе NEW
                         */
                        List<Assignment> assigmentList1 = AssignmentRepository.getAssignmentRepository().stream()
                                .filter(y -> y.getEmployee() == x)
                                .filter(y -> y.getTask().getPriority().getCode() >= task.getPriority().getCode())
                                .filter(y -> y.getStatus().equals(Status.NEW))
                                .toList();
                        return assigmentList1.isEmpty();
                    })
                    .toList();
            if (employeeList.isEmpty()) {
                List<Assignment> assigmentList = AssignmentRepository.getAssignmentRepository().stream()
                        .filter(x -> x.getEmployee().getSkill() == task.getSkill())
                        .filter(x -> x.getTask().getPriority().getCode() < task.getPriority().getCode())
                        .filter(x -> {
                            /**
                             * Ищем задачи сотрудника, где приоритет равен и больше переданной задачи,
                             * а так же чтобы статус был IN_PROGRESS.
                             * Если такие имеются, то сотрудник нам не подходит
                             */
                            List<Assignment> assigmentList1 = AssignmentRepository.getAssignmentRepository().stream()
                                    .filter(y -> y.getEmployee() == x.getEmployee())
                                    .filter(y -> y.getTask().getPriority().getCode() >= task.getPriority().getCode())
                                    .filter(y -> y.getStatus().equals(Status.IN_PROGRESS) || y.getStatus().equals(Status.NEW))
                                    .toList();
                            return assigmentList1.isEmpty();
                        })
                        .toList();
                if (!assigmentList.isEmpty()) {
                    Assignment assignment = assigmentList.get(0);
                    employee = assignment.getEmployee();
                }
            } else {
                employee = employeeList.get(0);
            }
        }
        return employee;
    }

    public static void planAllTask() throws Exception {
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        for (Task task : taskList) {
            planTask(task);
        }
    }

    public static Assignment planTask() throws Exception {
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        Task task = null;
        if (!taskList.isEmpty()) task = taskList.get(0);
        return planTask(task);
    }

    public static Assignment planTask(Task task) throws Exception {
        Assignment result = null;
        if (task != null) {
            Employee employee = findEmployee(task);
            if (employee != null) {
                result = createAssigment(employee, task);
            }
        }
        return result;
    }

    public static Assignment createAssigment(Employee employee, Task task) throws Exception {
        Assignment assignment = null;
        if (Checker.employeeIsNotNull(employee) && Checker.taskIsNotNull(task)) {
            if (task != null) {
                if (employee != null) {
                    assignment = new Assignment(employee, task);
                    AssignmentRepository.addAssignment(assignment);
                    if (assignment != null) FreeTaskRepository.deleteTask(task);
                }
            }
        }
        return assignment;
    }

    public static void reAssignToAnotherEmployee(List<Assignment> assignmentList) throws Exception {
        for (Assignment assignment : assignmentList) {
            reAssignToAnotherEmployee(assignment);
        }
    }

    public static void reAssignToAnotherEmployee(Assignment assignment) throws Exception {
        Employee employee = findEmployee(assignment.getTask());
        if (employee == null) {
            employee = EmployeeRepository.getEmployeeRepository().stream()
                    .filter(x -> x.getDepartment() == assignment.getEmployee().getDepartment())
                    .filter(x -> x.getSkill().equals(Skill.MANAGER))
                    .toList().get(0);
        }
        reAssignToAnotherEmployee(assignment, employee);
    }

    public static void reAssignToAnotherEmployee(Assignment assignment, Employee employee) throws Exception {
        assignment.setEmployee(employee);
    }
}
