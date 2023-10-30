package ru.lakeevda.lesson3.homework.entity.person;

import lombok.*;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.enums.Skill;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Employee extends Person {
    protected double salary;
    protected Department department;
    protected Skill skill;
    protected boolean isWorking = false;

    public Employee(String lastName, String firstName, LocalDate birthDate, double salary, Department department, Skill skill) {
        super(lastName, firstName, birthDate);
        this.salary = salary;
        this.department = department;
        this.skill = skill;
    }
}
