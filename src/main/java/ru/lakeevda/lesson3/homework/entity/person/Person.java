package ru.lakeevda.lesson3.homework.entity.person;

import java.time.LocalDate;

public class Person {
    protected int id;
    protected String lastName;
    protected String firstName;
    protected LocalDate birthDate;
    private static int count;

    public Person(String lastName, String firstName, LocalDate birthDate) {
        this.id = count++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
