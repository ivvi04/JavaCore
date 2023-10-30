package ru.lakeevda.lesson3.homework.entity.task;

import lombok.*;
import ru.lakeevda.lesson3.homework.enums.Priority;
import ru.lakeevda.lesson3.homework.enums.Skill;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Comparable<Task>{
    protected int id;
    protected String name;
    protected int rank;
    protected LocalDate createDate;
    protected LocalDate dueDate;
    protected Priority priority;
    protected Skill skill;
    protected int length;
    private static int count;

    public Task(String name, int rank, LocalDate dueDate, Skill skill, int length) {
        this(name, rank, dueDate, Priority.LOW, skill, length);
    }

    public Task(String name, int rank, LocalDate dueDate, Priority priority, Skill skill, int length) {
        this.id = count++;
        this.name = name;
        this.rank = rank;
        this.createDate = LocalDate.now();
        this.dueDate = dueDate;
        this.skill = skill;
        this.priority = priority;
        this.length = length;
    }

    public void setId(int id) {
        this.id = id;
        if (id >= count) count = ++id;
    }

    @Override
    public int compareTo(Task o) {
        return this.priority.getCode() - o.priority.getCode();
    }
}
