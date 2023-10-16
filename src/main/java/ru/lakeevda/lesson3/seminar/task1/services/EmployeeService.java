package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.classes.Assigment;
import ru.lakeevda.lesson3.seminar.task1.classes.Employee;
import ru.lakeevda.lesson3.seminar.task1.enums.Status;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;

import java.time.LocalDate;
import java.util.List;

public class EmployeeService {
    public List<Assigment> getAssigmentsByEmployee(Employee employee) {
        return AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee() == employee)
                .toList();
    };

    public void startTaskByEmployee(Employee employee, Assigment assigment){

        employee.setWorking(true);
        assigment.setFactStartDate(LocalDate.now());
        assigment.setStatus(Status.IN_PROGRESS);
    }

    public void finishTaskByEmployee(Employee employee, Assigment assigment){
        employee.setWorking(false);
        assigment.setFactEndDate(LocalDate.now());
        assigment.setStatus(Status.COMPLETE);
    }
}
