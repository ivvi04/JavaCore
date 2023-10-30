package ru.lakeevda.lesson3.homework.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.*;
import ru.lakeevda.lesson3.homework.entity.assigment.Assignment;
import ru.lakeevda.lesson3.homework.entity.department.Department;
import ru.lakeevda.lesson3.homework.entity.person.Employee;
import ru.lakeevda.lesson3.homework.entity.task.Task;
import ru.lakeevda.lesson3.homework.repository.AssignmentRepository;
import ru.lakeevda.lesson3.homework.repository.DepartmentRepository;
import ru.lakeevda.lesson3.homework.repository.EmployeeRepository;
import ru.lakeevda.lesson3.homework.repository.FreeTaskRepository;

import java.io.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FileService {

    private String path = "./data/";
    private String fileName;
    private String fileExtension = "json";
    private boolean withThrow = false;
    private ObjectMapper objectMapper;

    public FileService() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private File getFile() throws FileNotFoundException {
        String fullFileName = this.path + this.fileName + "." + this.fileExtension;
        File file = new File(fullFileName);
        if (this.withThrow && !file.exists()) throw new FileNotFoundException("File " + fullFileName + " not found");
        return file;
    }

    public void FileReader() throws IOException {
        DeparmentReader();
        EmployeeReader();
        FreeTaskReader();
        AssignmentReader();
    }

    public void DeparmentReader() throws IOException {
        this.setFileName(DepartmentRepository.class.getSimpleName());
        File file = getFile();
        if (file.exists()) {
            TypeReference<List<Department>> typeReference = new TypeReference<>() {};
            List<Department> repository = this.objectMapper.readValue(file, typeReference);
            DepartmentRepository.setDepartmentRepository(repository);

        }
    }

    public void EmployeeReader() throws IOException {
        this.setFileName(EmployeeRepository.class.getSimpleName());
        File file = getFile();
        if (file.exists()) {
            TypeReference<List<Employee>> typeReference = new TypeReference<>() {};
            List<Employee> repository = this.objectMapper.readValue(file, typeReference);
            for (Employee employee : repository) {
                /**
                 * Необходимо проверить почему при чтении объекты потом не совпадают
                 */
                List<Department> departmentList = DepartmentRepository.getDepartmentRepository().stream()
                        .filter(y -> y.equals(employee.getDepartment())).toList();
                if (!departmentList.isEmpty()) employee.setDepartment(departmentList.get(0));
            }
            EmployeeRepository.setEmployeeRepository(repository);

        }
    }

    public void FreeTaskReader() throws IOException {
        this.setFileName(FreeTaskRepository.class.getSimpleName());
        File file = getFile();
        if (file.exists()) {
            TypeReference<List<Task>> typeReference = new TypeReference<>() {};
            List<Task> repository = this.objectMapper.readValue(file, typeReference);
            FreeTaskRepository.setFreeTaskRepository(repository);

        }
    }

    public void AssignmentReader() throws IOException {
        this.setFileName(AssignmentRepository.class.getSimpleName());
        File file = getFile();
        if (file.exists()) {
            TypeReference<List<Assignment>> typeReference = new TypeReference<>() {};
            List<Assignment> repository = this.objectMapper.readValue(file, typeReference);
            for (Assignment assignment : repository) {
                /**
                 * Необходимо проверить почему при чтении объекты потом не совпадают
                 */
                List<Employee> employeeList = EmployeeRepository.getEmployeeRepository().stream()
                        .filter(y -> y.equals(assignment.getEmployee())).toList();
                if (!employeeList.isEmpty()) assignment.setEmployee(employeeList.get(0));
                List<Task> taskList = FreeTaskRepository.getFreeTaskRepository().stream()
                        .filter(y -> y.equals(assignment.getTask())).toList();
                if (!taskList.isEmpty()) assignment.setTask(taskList.get(0));
            }
            AssignmentRepository.setAssignmentRepository(repository);
        }
    }

    public void FileWriter() throws IOException {
        DepartmentWriter();
        EmployeeWriter();
        FreeTaskWriter();
        AssignmentWriter();
    }

    public void DepartmentWriter() throws IOException {
        this.setFileName(DepartmentRepository.class.getSimpleName());
        File file = getFile();
        this.objectMapper.writeValue(file, DepartmentRepository.getDepartmentRepository());
    }

    public void EmployeeWriter() throws IOException {
        this.setFileName(EmployeeRepository.class.getSimpleName());
        File file = getFile();
        this.objectMapper.writeValue(file, EmployeeRepository.getEmployeeRepository());
    }

    public void FreeTaskWriter() throws IOException {
        this.setFileName(FreeTaskRepository.class.getSimpleName());
        File file = getFile();
        this.objectMapper.writeValue(file, FreeTaskRepository.getFreeTaskRepository());
    }

    public void AssignmentWriter() throws IOException {
        this.setFileName(AssignmentRepository.class.getSimpleName());
        File file = getFile();
        this.objectMapper.writeValue(file, AssignmentRepository.getAssignmentRepository());
    }
}
