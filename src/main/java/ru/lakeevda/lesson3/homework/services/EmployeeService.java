package ru.lakeevda.lesson3.homework.services;

import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.enums.Skill;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.enums.Status;
import ru.lakeevda.lesson3.homework.exceptions.EmployeeException;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.repository.DepartmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

public class EmployeeService {

    public List<Employee> getEmployeeRepository() {
        return EmployeeRepository.getEmployeeRepository();
    }

    public List<Assignment> getAssignmentsByEmployee(Employee employee) {
        return AssignmentRepository.getAssignmentRepository().stream()
                .filter(x -> x.getEmployee().equals(employee))
                .toList();
    }

    public Employee getEmployeeById(int employeeId) {
        Employee result = null;
        List<Employee> employeeList = EmployeeRepository.getEmployeeRepository().stream()
                .filter(x -> x.getId() == employeeId).toList();
        if (!employeeList.isEmpty()) result = employeeList.get(0);
        return result;
    }

    public boolean canWorkWithAssignmentByEmployee(Employee employee, Assignment assignment) throws EmployeeException {
        return canWorkWithAssignmentByEmployee(employee, assignment, true);
    }

    public boolean canWorkWithAssignmentByEmployee(Employee employee, Assignment assignment, boolean withThrow) throws EmployeeException {
        boolean result = true;
        if (assignment != null && !employee.equals(assignment.getEmployee())) {
            if (employee.getSkill().equals(Skill.MANAGER)
                    && assignment.getEmployee().getDepartment() == employee.getDepartment()) ;
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
            List<Assignment> assignmentList = getAssignmentsByEmployee(employee).stream()
                    .filter(x -> x.getStatus().equals(Status.IN_PROGRESS))
                    .toList();
            if (!assignmentList.isEmpty()) {
                if (forcibly) {
                    for (Assignment assignment1 : assignmentList) {
                        onHoldAssignmentByEmployee(employee, assignment1);
                    }
                } else throw new EmployeeException("The employee already has a assignment");
            }
            assignment.getEmployee().setWorking(true);
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
            List<Assignment> assigmentList = AssignmentRepository.getAssignmentRepository().stream()
                    .filter(x -> x.getEmployee().equals(employee))
                    .filter(x -> x.getStatus().equals(Status.ON_HOLD))
                    .max(Comparator.comparingInt(x -> x.getTask().getPriority().getCode()))
                    .stream().toList();
            if (!assigmentList.isEmpty()) startAssignmentByEmployee(employee, assigmentList.get(0));
        }
    }

    public void addEmployeeConsole() {
        Scanner scanner = new Scanner(System.in, Charset.forName("windows-1251"));
        System.out.println("Введите Фамилию:");
        String lastName = scanner.next();
        System.out.println("Введите Имя:");
        String firstName = scanner.next();
        LocalDate birthDate;
        while (true) {
            System.out.println("Введите Дату рождения (Формат ГГГГ-ММ-ДД):");
            try {
                birthDate = LocalDate.parse(scanner.next());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат даты рождения. Повторите ввод");
            }
        }
        double salary;
        while (true) {
            System.out.println("Введите Зарплату:");
            try {
                salary = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат зарплаты. Повторите ввод");
            }
        }
        Department department;
        while (true) {
            System.out.println("Выберете Отдел по id:");
            System.out.println(DepartmentRepository.getDepartmentRepository());
            try {
                int departmentId = scanner.nextInt();
                DepartmentService departmentService = new DepartmentService();
                department = departmentService.getDepartmentById(departmentId);
                if (department == null) throw new EmployeeException();
                break;
            } catch (InputMismatchException | EmployeeException e) {
                System.out.println("Неверный id отдела. Повторите ввод");
            }
        }
        Skill skill;
        while (true) {
            System.out.println("Выберете Навык из списка:");
            for (Skill skill1 : Skill.values()) System.out.print(skill1 + " ");
            System.out.println();
            try {
                skill = Skill.valueOf(scanner.next());
                if (skill == null) throw new EmployeeException();
                break;
            } catch (InputMismatchException | EmployeeException e) {
                System.out.println("Неверный навык. Повторите ввод");
            }
        }
        addEmployee(lastName, firstName, birthDate, salary, department, skill);
    }

    public void addEmployee(String lastName, String firstName, LocalDate birthDate, double salary,
                            Department department, Skill skill) {
        Employee employee = new Employee(lastName, firstName, birthDate, salary, department, skill);
        addEmployeeToRepository(employee);
    }

    public void addEmployeeToRepository(Employee employee) {
        EmployeeRepository.addEmployee(employee);
    }
}
