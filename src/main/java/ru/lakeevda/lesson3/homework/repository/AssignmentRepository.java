package ru.lakeevda.lesson3.homework.repository;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

abstract public class AssignmentRepository {
    static private List<Assignment> assignmentRepository;

    static public List<Assignment> addAssignment(Assignment assignment) {
        createAssignmentRepository();
        assignmentRepository.add(assignment);
        return assignmentRepository;
    }

    static public List<Assignment> getRepository() {
        createAssignmentRepository();
        return assignmentRepository.stream()
                .sorted(Comparator.comparingInt(x -> (x.getTask().getPriority().getCode()))).toList();
    }

    static public Assignment getAssignmentById(int id) {
        createAssignmentRepository();
        Assignment result = null;
        List<Assignment> assignmentList = assignmentRepository.stream()
                .filter(x -> x.getId() == id).toList();
        if (!assignmentList.isEmpty()) result = assignmentList.get(0);
        return result;
    }

    static private void createAssignmentRepository() {
        if (assignmentRepository == null) assignmentRepository = new ArrayList<>();
    }

}
