package ru.lakeevda.lesson3.homework.repository;


import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.exceptions.DepartmentException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

abstract public class FreeTaskRepository {
    private static List<Task> freeTaskRepository;

    public static List<Task> getFreeTaskRepository() {
        List<Task> result = null;
        createFreeTaskRepository();
        result = freeTaskRepository.stream()
                .sorted(Comparator.comparingInt(x -> (x.getPriority().getCode())))
                .toList();
        return result;
    }

    public static void setFreeTaskRepository(List<Task> freeTaskList) {
        createFreeTaskRepository();
        if (freeTaskList != null) freeTaskRepository.addAll(freeTaskList);
    }

    public static List<Task> addTask(Task task) {
        createFreeTaskRepository();
        freeTaskRepository.add(task);
        return freeTaskRepository;
    }

    public static void deleteTask(Task task) {
        deleteTask(freeTaskRepository.indexOf(task));
    }

    public static void deleteTask(int index) {
        freeTaskRepository.remove(index);
    }

    private static void createFreeTaskRepository() {
        if (freeTaskRepository == null) freeTaskRepository = new ArrayList<>();
    }
}
