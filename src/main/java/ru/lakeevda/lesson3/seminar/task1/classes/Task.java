package ru.lakeevda.lesson3.seminar.task1.classes;

import ru.lakeevda.lesson3.seminar.task1.enums.Priority;
import ru.lakeevda.lesson3.seminar.task1.enums.Skill;

import java.time.LocalDate;

public class Task {
    private String name;
    private int rank;
    private LocalDate createDate;
//    private LocalDate beginDate;
//    private LocalDate factEndDate;
    private LocalDate dueDate;
    private Priority priority;
    private Skill skill;

    private int length;

    public Task(String name, int rank, LocalDate dueDate, Skill skill, int length) {
        this.name = name;
        this.rank = rank;
        this.createDate = LocalDate.now();
        this.dueDate = dueDate;
        this.skill = skill;
        this.priority = Priority.P2;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int range) {
        this.rank = range;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

//    public LocalDate getBeginDate() {
//        return beginDate;
//    }
//
//    public void setBeginDate(LocalDate beginDate) {
//        this.beginDate = beginDate;
//    }
//
//    public LocalDate getFactEndDate() {
//        return factEndDate;
//    }
//
//    public void setFactEndDate(LocalDate factEndDate) {
//        this.factEndDate = factEndDate;
//    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                ", createDate=" + createDate +
//                ", beginDate=" + beginDate +
//                ", factEndDate=" + factEndDate +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", skill=" + skill +
                '}';
    }
}
