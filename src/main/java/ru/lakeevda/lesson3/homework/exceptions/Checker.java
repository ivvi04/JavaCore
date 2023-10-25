package ru.lakeevda.lesson3.homework.exceptions;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.person.Manager;
import ru.lakeevda.lesson3.homework.entity.task.Task;

abstract public class Checker {

    static public boolean departmentIsNotNull(Department department) throws DepartmentException {
        return departmentIsNotNull(department, true);
    }

    static public boolean departmentIsNotNull(Department department, boolean withThrow) throws DepartmentException {
        boolean result = true;
        if (department == null) {
            result = false;
            if (withThrow) throw new DepartmentException("Department is null");
        }
        return result;
    }

    static public boolean employeeIsNotNull(Employee employee) throws EmployeeException {
        return employeeIsNotNull(employee, true);
    }

    static public boolean employeeIsNotNull(Employee employee, boolean withThrow) throws EmployeeException {
        boolean result = true;
        if (employee == null) {
            result = false;
            if (withThrow) throw new EmployeeException("Employee is null");
        }
        return result;
    }

    static public boolean managerIsNotNull(Manager manager) throws ManagerException {
        return managerIsNotNull(manager, true);
    }

    static public boolean managerIsNotNull(Manager manager, boolean withThrow) throws ManagerException {
        boolean result = true;
        if (manager == null) {
            result = false;
            if (withThrow) throw new ManagerException("Employee is null");
        }
        return result;
    }

    static public boolean taskIsNotNull(Task task) throws TaskException {
        return taskIsNotNull(task, true);
    }

    static public boolean taskIsNotNull(Task task, boolean withThrow) throws TaskException {
        boolean result = true;
        if (task == null) {
            result = false;
            if (withThrow) throw new TaskException("Task is null");
        }
        return result;
    }

    static public boolean assignmentIsNotNull(Assignment assignment) throws AssignmentException {
        return assignmentIsNotNull(assignment, true);
    }

    static public boolean assignmentIsNotNull(Assignment assignment, boolean withThrow) throws AssignmentException {
        boolean result = true;
        if (assignment == null) {
            result = false;
            if (withThrow) throw new AssignmentException("Assignment is null");
        }
        return result;
    }
}
