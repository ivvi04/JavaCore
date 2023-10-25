package ru.lakeevda.lesson3.homework.services;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.exceptions.EmployeeException;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {
    public List<Assignment> getAssigmentsByEmployee(Employee employee) {
        return AssignmentRepository.getRepository().stream()
                .filter(x -> x.getEmployee() == employee)
                .toList();
    }

    public boolean canWorkWithAssignmentByEmployee(Employee employee, Assignment assignment) throws EmployeeException {
        return canWorkWithAssignmentByEmployee(employee, assignment, true);
    }

    public boolean canWorkWithAssignmentByEmployee(Employee employee, Assignment assignment, boolean withThrow) throws EmployeeException {
        boolean result = true;
        if (assignment != null && !employee.equals(assignment.getEmployee())) {
            if (employee.getSkill().equals(Skill.MANAGER)
                    && assignment.getEmployee().getDepartment().getManager().equals(employee)) ;
            else {
                result = false;
                if (withThrow) throw new EmployeeException("This assignment cannot be started by this employee");
            }
        }
        return result;
    }

    public void startAssignmentByEmployee(Employee employee, int assignmentId) throws EmployeeException {
        startAssignmentByEmployee(employee, AssignmentRepository.getAssignmentById(assignmentId), true);
    }

    public void startAssignmentByEmployee(Employee employee, int assignmentId, boolean forcibly) throws EmployeeException {
        startAssignmentByEmployee(employee, AssignmentRepository.getAssignmentById(assignmentId), forcibly);
    }

    public void startAssignmentByEmployee(Employee employee, Assignment assignment) throws EmployeeException {
        startAssignmentByEmployee(employee, assignment, true);
    }

    public void startAssignmentByEmployee(Employee employee, Assignment assignment, boolean forcibly) throws EmployeeException {
        if (assignment != null) {
            canWorkWithAssignmentByEmployee(employee, assignment);
            if (!employee.equals(assignment.getEmployee())) employee = assignment.getEmployee();
            List<Assignment> assignmentList = getAssigmentsByEmployee(employee).stream()
                    .filter(x -> x.getStatus().equals(Status.IN_PROGRESS))
                    .toList();
            if (!assignmentList.isEmpty()) {
                if (forcibly) {
                    for (Assignment assignment1 : assignmentList) {
                        onHoldAssignmentByEmployee(employee, assignment1);
                    }
                } else throw new EmployeeException("The employee already has a assignment");
            }
            employee.setWorking(true);
            if (assignment.getFactStartDate() == null) assignment.setFactStartDate(LocalDate.now());
            assignment.setStatus(Status.IN_PROGRESS);
        }
    }

    public void onHoldAssignmentByEmployee(Employee employee, int assignmentId) throws EmployeeException {
        onHoldAssignmentByEmployee(employee, AssignmentRepository.getAssignmentById(assignmentId));
    }

    public void onHoldAssignmentByEmployee(Employee employee, Assignment assignment) throws EmployeeException {
        canWorkWithAssignmentByEmployee(employee, assignment);
        assignment.setStatus(Status.ON_HOLD);
    }

    public void finishAssignmentByEmployee(Employee employee, int assignmentId) throws EmployeeException {
        finishAssignmentByEmployee(employee, AssignmentRepository.getAssignmentById(assignmentId));
    }

    public void finishAssignmentByEmployee(Employee employee, Assignment assignment) throws EmployeeException {
        if (assignment != null) {
            if (canWorkWithAssignmentByEmployee(employee, assignment)) {
                if (!employee.equals(assignment.getEmployee())) employee = assignment.getEmployee();
                employee.setWorking(false);
                assignment.setFactEndDate(LocalDate.now());
                assignment.setStatus(Status.COMPLETE);
                startNextAssignmentByEmployee(employee);
            }
        }
    }

    public void startNextAssignmentByEmployee(Employee employee) throws EmployeeException {
        if (!employee.isWorking()) {
            List<Assignment> assigmentList = AssignmentRepository.getRepository().stream()
                    .filter(x -> x.getEmployee() == employee)
                    .filter(x -> x.getStatus().equals(Status.ON_HOLD))
                    .max(Comparator.comparingInt(x -> x.getTask().getPriority().getCode()))
                    .stream().toList();
            if (!assigmentList.isEmpty()) startAssignmentByEmployee(employee, assigmentList.get(0));
        }
    }
}
