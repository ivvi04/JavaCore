package ru.lakeevda.lesson3.homework.planner;

import ru.lakeevda.lesson3.homework.entity.assigment.Assigment;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.enums.parameters.PriorityParams;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.repository.AssigmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;

import java.util.List;

abstract public class TaskPlanner {
    public static Assigment planTask(Task task) {
        List<Employee> employeeList = EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getSkill() == task.getSkill())
                .filter(x -> !x.isWorking())
                .toList();
        if (!employeeList.isEmpty()) return createAssigment(employeeList.get(0), task);
        else return planPriorityTask(task);
    }

    public static Assigment planPriorityTask(Task task) {
        Employee employee = null;
        List<Assigment> assigmentList = AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee().getSkill() == task.getSkill())
                .filter(x -> PriorityParams.priorityEnumMap.get(x.getTask().getPriority()) <
                        PriorityParams.priorityEnumMap.get(task.getPriority()))
                .filter(x -> {
                    /**
                     * Ищем задачи сотрудника, где приоритет равен и больше переданной задачи,
                     * а так же чтобы статус был IN_PROGRESS.
                     * Если такие имеются, то сотрудник нам не подходит
                     */
                    List<Assigment> assigmentList1 = AssigmentRepository.getAssigmentList().stream()
                            .filter(y -> y.getEmployee() == x.getEmployee())
                            .filter(y -> PriorityParams.priorityEnumMap.get(y.getTask().getPriority()) >=
                                    PriorityParams.priorityEnumMap.get(task.getPriority()))
                            .filter(y -> y.getStatus().equals(Status.IN_PROGRESS))
                            .toList();
                    return assigmentList1.isEmpty();
                })
                .toList();
        if (!assigmentList.isEmpty()) {
            Assigment assigment = assigmentList.get(0);
            employee = assigment.getEmployee();
        }
        return createAssigment(employee, task);
    }

    private static Assigment createAssigment(Employee employee, Task task) {
        Assigment assigment = null;
        if (employee != null && task != null) {
            assigment = new Assigment(employee, task);
            AssigmentRepository.addAssigment(assigment);
        }
        return assigment;
    }
}
