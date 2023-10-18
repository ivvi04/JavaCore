package ru.lakeevda.lesson3.homework.entity.person;

import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.enums.Skill;

import java.time.LocalDate;

public class Manager extends Employee {
    public Manager(String lastName, String firstName, LocalDate birthDate, double salary, Department department, Skill skill) {
        super(lastName, firstName, birthDate, salary, department, skill);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", department=" + department +
                ", skill=" + skill +
                ", isWorking=" + isWorking +
                '}';
    }
}
