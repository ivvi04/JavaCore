package ru.lakeevda.lesson3.homework.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

abstract public class AssignmentRepository {
    private static List<Assignment> assignmentRepository;

    public static List<Assignment> getAssignmentRepository() {
        createAssignmentRepository();
        return assignmentRepository.stream()
                .sorted(Comparator.comparingInt(x -> (x.getTask().getPriority().getCode()))).toList();
    }

    public static void setAssignmentRepository(List<Assignment> assignmentList) {
        createAssignmentRepository();
        if (assignmentList != null) assignmentRepository.addAll(assignmentList);
    }

    public static List<Assignment> addAssignment(Assignment assignment) {
        createAssignmentRepository();
        assignmentRepository.add(assignment);
        return assignmentRepository;
    }

    public static Assignment getAssignmentById(int id) {
        Assignment result = null;
        List<Assignment> assignmentList = assignmentRepository.stream()
                .filter(x -> x.getId() == id).toList();
        if (!assignmentList.isEmpty()) result = assignmentList.get(0);
        return result;
    }

    private static void createAssignmentRepository() {
        if (assignmentRepository == null) assignmentRepository = new ArrayList<>();
    }
}
