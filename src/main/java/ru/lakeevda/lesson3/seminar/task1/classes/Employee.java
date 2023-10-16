package ru.lakeevda.lesson3.seminar.task1.classes;

import ru.lakeevda.lesson3.seminar.task1.enums.Skill;

import java.time.LocalDate;

public class Employee {
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private double salary;
    private Department department;
    private Skill skill;

    private boolean isWorking = false;

    public Employee(String lastName, String firstName, LocalDate birthDate, double salary, Department department, Skill skill) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.department = department;
        this.skill = skill;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", department=" + department +
                ", skill=" + skill +
                '}';
    }
}
