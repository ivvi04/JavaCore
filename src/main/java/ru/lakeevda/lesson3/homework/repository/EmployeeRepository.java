package ru.lakeevda.lesson3.homework.repository;

import ru.lakeevda.lesson3.homework.entity.person.Employee;

import java.util.ArrayList;
import java.util.List;

abstract public class EmployeeRepository {

    static private List<Employee> employeeRepository;

    static public List<Employee> addEmployee(Employee employee){
        createEmployeeRepository();
        employeeRepository.add(employee);
        return employeeRepository;
    }

    static public List<Employee> getRepository() {
        createEmployeeRepository();
        return employeeRepository;
    }

    static private void createEmployeeRepository() {
        if (employeeRepository == null) employeeRepository = new ArrayList<>();
    }
}
