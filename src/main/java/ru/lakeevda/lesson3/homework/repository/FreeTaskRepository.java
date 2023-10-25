package ru.lakeevda.lesson3.homework.repository;


import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.exceptions.Checker;
import ru.lakeevda.lesson3.homework.exceptions.DepartmentException;

import java.util.*;

public class FreeTaskRepository {
    static private Map<Department, List<Task>> freeTaskRepository;

    static public List<Task> addTask(Department department, Task task) throws DepartmentException {
        List<Task> result = null;
        if (Checker.departmentIsNotNull(department)) {
            createFreeTaskRepository();
            result = freeTaskRepository.get(department);
            if (result == null) result = new ArrayList<>();
            result.add(task);
            freeTaskRepository.put(department, result);
        }
        return result;
    }

    static public Map<Department, List<Task>> getRepository() {
        createFreeTaskRepository();
        return freeTaskRepository;
    }

    static public List<Task> getTasks(Department department) throws DepartmentException {
        List<Task> result = null;
        if (Checker.departmentIsNotNull(department)) {
            createFreeTaskRepository();
            result = freeTaskRepository.get(department).stream()
                    .sorted(Comparator.comparingInt(x -> (x.getPriority().getCode())))
                    .toList();
        }
        return result;
    }

    static public Task getFirstTask(Department department) throws DepartmentException {
        Task result = null;
        if (Checker.departmentIsNotNull(department)) result = freeTaskRepository.get(department).get(0);
        return result;
    }

    static public void deleteTask(Department department, Task task) throws DepartmentException {
        deleteTask(department, freeTaskRepository.get(department).indexOf(task));
    }

    static public void deleteTask(Department department, int index) throws DepartmentException {
        if (Checker.departmentIsNotNull(department)) freeTaskRepository.get(department).remove(index);
    }

    static private void createFreeTaskRepository() {
        if (freeTaskRepository == null) freeTaskRepository = new HashMap<>();
    }

}
