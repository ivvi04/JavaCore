package ru.lakeevda.lesson3.homework.entity.assigment;

import lombok.*;
import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    protected int id;
    protected Employee employee;
    protected Task task;
    protected LocalDate factStartDate;
    protected LocalDate factEndDate;
    protected Status status;
    private static int count;

    public Assignment(Employee employee, Task task) {
        this.id = count++;
        this.employee = employee;
        this.task = task;
        this.status = Status.NEW;
    }

    public void setId(int id) {
        this.id = id;
        if (id >= count) count = ++id;
    }
}
