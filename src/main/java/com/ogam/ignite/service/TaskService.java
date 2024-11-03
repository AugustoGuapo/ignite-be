package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.EmployeeDTO;
import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.dtos.TaskDTO;
import com.ogam.ignite.domain.requests.AddAssigneeRequest;
import com.ogam.ignite.domain.requests.AddTaskRequest;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.repository.EmployeeRepository;
import com.ogam.ignite.repository.ProjectRepository;
import com.ogam.ignite.repository.TaskRepository;
import org.springframework.stereotype.Service;

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
        TaskDTO toSave = TaskDTO.transformRequestToDTO(task);
        toSave.setProject(projectRepository.findById(task.getProjectId()).orElseThrow(
                () -> new DataNotFoundException(String.format("Project not found for id: %d", task.getProjectId()))));
        return taskRepository.save(toSave).getProject();
    }

    public TaskDTO addAssignee(AddAssigneeRequest assigneeRequest, Long taskId) {
        TaskDTO toUpdate = taskRepository.findById(taskId).orElseThrow(
                () -> new DataNotFoundException(String.format("Task not found for id: %d", taskId)));
        EmployeeDTO assignee = employeeRepository.findById(assigneeRequest.getEmployeeId()).orElseThrow(
                () -> new DataNotFoundException(
                        String.format("Employee not found for id: %d", assigneeRequest.getEmployeeId())));
        toUpdate.setAssignee(assignee);
        return taskRepository.save(toUpdate);
    }
}
