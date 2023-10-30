package ru.lakeevda.lesson3.homework.services;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.exceptions.EmployeeException;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.repository.DepartmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DepartmentService {
    public List<Department> getDepartmentRepository() {
        return DepartmentRepository.getDepartmentRepository();
    }

    public Department getDepartmentByIndex(int index) {
        Department result = null;
        result = DepartmentRepository.getDepartmentRepository().get(index);
        return result;
    }

    public Department getDepartmentById(int departmentId) {
        Department result = null;
        List<Department> departmentList = DepartmentRepository.getDepartmentRepository().stream()
                .filter(x -> x.getId() == departmentId).toList();
        if (!departmentList.isEmpty()) result = departmentList.get(0);
        return result;
    }

    public void addDepartmentConsole() {
        Scanner scanner = new Scanner(System.in, Charset.forName("windows-1251"));
        System.out.println("Введите Название отдела:");
        String name = scanner.next();
        addDepartment(name);
    }

    public void addDepartment(String name) {
        Department department = new Department(name);
        addDepartmentToRepository(department);
    }

    public void addDepartmentToRepository(Department department) {
        DepartmentRepository.addDepartment(department);
    }
}
