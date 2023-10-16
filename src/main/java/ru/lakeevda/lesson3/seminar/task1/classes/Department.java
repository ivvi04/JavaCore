package ru.lakeevda.lesson3.seminar.task1.classes;

public class Department {
    private String name;
    private Employee manager;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}
