package ru.lakeevda.lesson3.homework.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class EmployeeRepository {
    private static List<Employee> employeeRepository;

    public static List<Employee> addEmployee(Employee employee) {
        createEmployeeRepository();
        employeeRepository.add(employee);
        return employeeRepository;
    }

    public static List<Employee> getEmployeeRepository() {
        createEmployeeRepository();
        return employeeRepository;
    }

    public static void setEmployeeRepository(List<Employee> employeeList) {
        createEmployeeRepository();
        if (employeeList != null) employeeRepository.addAll(employeeList);
    }

    private static void createEmployeeRepository() {
        if (employeeRepository == null) employeeRepository = new ArrayList<>();
    }
}
