package ru.lakeevda.lesson3.homework.entity.department;

import lombok.*;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.entity.person.Employee;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    protected int id;
    protected String name;
    private static int count;

    public Department(String name) {
        this.id = count++;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
        if (id >= count) count = ++id;
    }
}
