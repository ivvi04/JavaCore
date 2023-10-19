package ru.lakeevda.lesson3.homework.services;

import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.person.Manager;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;

public class ManagerService {
    public void increaseSalaryByManagerByEmployee(Manager manager, Employee employee, double amount) {
        if (amount <= 0) throw new RuntimeException("The amount is less than or equal to zero");
        if (!manager.getSkill().equals(Skill.MANAGER)) throw new RuntimeException("Only a manager can increase salary");
        if (manager.equals(employee)) throw new RuntimeException("A manager cannot increase his own salary");
        if (!manager.getDepartment().equals(employee.getDepartment()))
            throw new RuntimeException("A manager can increase salary only in his own department");
        employee.setSalary(employee.getSalary() + amount);
    }
    public void increaseSalaryByManagerAllEmployees(Manager manager, double amount) {
        if (amount <= 0) throw new RuntimeException("The amount is less than or equal to zero");
        EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getDepartment().equals(manager.getDepartment()))
                .filter(x -> !x.equals(manager))
                .forEach(x -> {
                    increaseSalaryByManagerByEmployee(manager, x, amount);
                });
    }
}
