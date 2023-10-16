package ru.lakeevda.lesson3.seminar.task1.repository;

import ru.lakeevda.lesson3.seminar.task1.classes.Employee;

import java.util.ArrayList;
import java.util.List;

abstract public class EmployeeRepository {

    static private List<Employee> employees;

    static public List<Employee> addEmployee(Employee employee){
        if (employees == null) employees = new ArrayList<>();
        employees.add(employee);
        return employees;
    }

    static public List<Employee> getEmployees() {
        return employees;
    }
}
