package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.dtos.TaskDTO;
import com.ogam.ignite.domain.requests.AddTaskRequest;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.repository.ProjectRepository;
import com.ogam.ignite.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    ProjectRepository projectRepository;
    TaskRepository taskRepository;

    public TaskService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public ProjectDTO addNewTask(AddTaskRequest task) {
        TaskDTO toSave = TaskDTO.transformRequestToDTO(task);
        toSave.setProject(projectRepository.findById(task.getProjectId()).orElseThrow(
                () -> new DataNotFoundException(String.format("Project not found for id: %d", task.getProjectId()))));
        return taskRepository.save(toSave).getProject();
    }
}
