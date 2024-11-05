package com.ogam.ignite.controller;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.dtos.TaskDTO;
import com.ogam.ignite.domain.requests.AddAssigneeRequest;
import com.ogam.ignite.domain.requests.AddTaskRequest;
import com.ogam.ignite.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<ProjectDTO> addNewTask(@RequestBody AddTaskRequest task) {
        return ResponseEntity.ok(taskService.addNewTask(task));
    }

    @PostMapping("/tasks/{taskId}/add_assignee")
    public ResponseEntity<TaskDTO> addAssigneeToATask(
            @RequestBody AddAssigneeRequest request, @PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.addAssignee(request, taskId));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

}
