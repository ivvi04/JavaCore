package ru.lakeevda.lesson3.homework.exceptions;

public class TaskException extends Exception {
    public TaskException() {
    }

    public TaskException(String message) {
        super(message);
    }
}
