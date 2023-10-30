package ru.lakeevda.lesson3.homework.services;

import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.enums.Priority;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.exceptions.EmployeeException;
import ru.lakeevda.lesson3.homework.exceptions.TaskException;
import ru.lakeevda.lesson3.homework.repository.DepartmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;
import ru.lakeevda.lesson3.homework.repository.FreeTaskRepository;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskService {
    public List<Task> getFreeTaskRepository() {
        return FreeTaskRepository.getFreeTaskRepository();
    }
    public Task getFirstTask() {
        Task result = null;
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        if (!taskList.isEmpty()) result = taskList.get(0);
        return result;
    }

    public Task getTaskByIndex(int index) {
        Task result = null;
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        if (!taskList.isEmpty()) result = taskList.get(index);
        return result;
    }

    public Task getTaskById(int taskId) {
        Task result = null;
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        if (!taskList.isEmpty()) {
            List<Task> resultList = taskList.stream().filter(x -> x.getId() == taskId).toList();
            if (!resultList.isEmpty()) result = resultList.get(0);
        }
        return result;
    }

    public void addTaskConsole() {
        Scanner scanner = new Scanner(System.in, Charset.forName("windows-1251"));
        System.out.println("Введите Название задачи:");
        String name = scanner.next();
        int rank;
        while (true) {
            System.out.println("Введите Ранг:");
            try {
                rank = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат ранга. Повторите ввод");
            }
        }
        LocalDate dueDate;
        while (true) {
            System.out.println("Введите Дату окончания (Формат ГГГГ-ММ-ДД):");
            try {
                dueDate = LocalDate.parse(scanner.next());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат даты окончания. Повторите ввод");
            }
        }
        Priority priority;
        while (true) {
            System.out.println("Выберете Приоритет из списка:");
            for (Priority priority1 : Priority.values()) System.out.print(priority1 + " ");
            System.out.println();
            try {
                priority = Priority.valueOf(scanner.next());
                if (priority == null) throw new TaskException();
                break;
            } catch (InputMismatchException | TaskException e) {
                System.out.println("Неверный приоритет. Повторите ввод");
            }
        }
        Skill skill;
        while (true) {
            System.out.println("Выберете Навык из списка:");
            for (Skill skill1 : Skill.values()) System.out.print(skill1 + " ");
            try {
                skill = Skill.valueOf(scanner.next());
                if (skill == null) throw new TaskException();
                break;
            } catch (InputMismatchException | TaskException e) {
                System.out.println("Неверный навык. Повторите ввод");
            }
        }
        int length;
        while (true) {
            System.out.println("Введите Длину:");
            try {
                length = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат длины. Повторите ввод");
            }
        }
        addTask(name, rank, dueDate, priority, skill, length);
    }

    public void addTask(String name, int rank, LocalDate dueDate, Priority priority, Skill skill, int length) {
        Task task = new Task(name, rank, dueDate, priority, skill, length);
        addTaskToRepository(task);
    }

    public void addTaskToRepository(Task task) {
        FreeTaskRepository.addTask(task);
    }
}
