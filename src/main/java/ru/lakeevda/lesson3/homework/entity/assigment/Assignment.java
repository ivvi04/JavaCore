package ru.lakeevda.lesson3.homework.entity.assigment;

import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;

import java.time.LocalDate;

public class Assignment {
    private int id;
    private Employee employee;
    private Task task;
    private LocalDate factStartDate;
    private LocalDate factEndDate;
    private Status status;
    private static int count;

    public Assignment(Employee employee, Task task) {
        this.id = count++;
        this.employee = employee;
        this.task = task;
        this.status = Status.NEW;
    }

    public int getId() {
        return id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Task getTask() {
        return task;
    }

    public LocalDate getFactStartDate() {
        return factStartDate;
    }

    public void setFactStartDate(LocalDate factStartDate) {
        this.factStartDate = factStartDate;
    }

    public LocalDate getFactEndDate() {
        return factEndDate;
    }

    public void setFactEndDate(LocalDate factEndDate) {
        this.factEndDate = factEndDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Assigment{" +
                "id=" + id +
                ", employee=" + employee +
                ", task=" + task +
                ", factStartDate=" + factStartDate +
                ", factEndDate=" + factEndDate +
                ", status=" + status +
                '}' + "\n";
    }
}
