package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.dtos.TaskDTO;
import com.ogam.ignite.domain.entities.Employee;
import com.ogam.ignite.domain.entities.Task;
import com.ogam.ignite.domain.requests.AddAssigneeRequest;
import com.ogam.ignite.domain.requests.AddTaskRequest;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.repository.EmployeeRepository;
import com.ogam.ignite.repository.ProjectRepository;
import com.ogam.ignite.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ogam.ignite.utils.Utils.updateField;

@Service
public class TaskService {

    ProjectRepository projectRepository;
    TaskRepository taskRepository;
    EmployeeRepository employeeRepository;

    public TaskService(ProjectRepository projectRepository,
                       TaskRepository taskRepository,
                       EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public ProjectDTO addNewTask(AddTaskRequest task) {
        Task toSave = task.getTaskId() == null ? Task.transformRequestToEntity(task)
                : taskRepository.findById(task.getTaskId()).orElseThrow(RuntimeException::new);
        if(task.getTaskId() != null) {
            toSave.setName(updateField(toSave.getName(), task.getName()));
            toSave.setPrice(updateField(toSave.getPrice(), task.getPrice()));
            toSave.setDescription(updateField(toSave.getDescription(), task.getDescription()));
            toSave.setGrade(updateField(toSave.getGrade(), task.getGrade()));
            toSave.setAssignee(task.getEmployeeId() == null ? toSave.getAssignee() == null ? null: toSave.getAssignee()
                    : employeeRepository.findById(task.getEmployeeId()).orElseThrow(RuntimeException::new));
            toSave.setStatus(updateField(toSave.getStatus(), task.getStatus()));
        } else {
            toSave.setAssignee(employeeRepository.findById(task.getEmployeeId()).orElseThrow(
                    () -> new DataNotFoundException(
                            String.format("Employee not found for id: %d", task.getEmployeeId()))));
            toSave.setProject(projectRepository.findById(task.getProjectId()).orElseThrow(
                    () -> new DataNotFoundException(String.format("Project not found for id: %d", task.getProjectId()))));
        }
        return ProjectDTO.transformEntityToDTO(taskRepository.save(toSave).getProject());
    }

    public TaskDTO addAssignee(AddAssigneeRequest assigneeRequest, Long taskId) {
        Task toUpdate = taskRepository.findById(taskId).orElseThrow(
                () -> new DataNotFoundException(String.format("Task not found for id: %d", taskId)));
        Employee assignee = employeeRepository.findById(assigneeRequest.getEmployeeId()).orElseThrow(
                () -> new DataNotFoundException(
                        String.format("Employee not found for id: %d", assigneeRequest.getEmployeeId())));
        toUpdate.setAssignee(assignee);
        return TaskDTO.transformEntityToDTO(taskRepository.save(toUpdate));
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(entity -> TaskDTO.transformEntityToDTO(entity)).toList();
    }
}
