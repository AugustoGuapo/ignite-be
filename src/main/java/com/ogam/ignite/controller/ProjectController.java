package com.ogam.ignite.controller;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.requests.AddProjectRequest;
import com.ogam.ignite.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService service) {
        this.projectService = service;
    }

    @PostMapping("/projects")
    ResponseEntity<ProjectDTO> addNewProject(@RequestBody AddProjectRequest project) {
        return ResponseEntity.ok(projectService.addNewProject(project));
    }
}
