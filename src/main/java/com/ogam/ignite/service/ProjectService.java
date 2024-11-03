package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.entities.Project;
import com.ogam.ignite.domain.requests.AddProjectRequest;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.repository.ClientRepository;
import com.ogam.ignite.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private ClientRepository clientRepository;

    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
    }

    public ProjectDTO addNewProject(AddProjectRequest project) {
        Project toSave = Project.transformRequestToEntity(project);
        toSave.setClient(clientRepository.findById(project.getClientId()).orElseThrow(()
                -> new DataNotFoundException(String.format("Client not found for id: %d", project.getClientId()))));
        return ProjectDTO.transformEntityToDTO(projectRepository.save(toSave));
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream().map(entity -> ProjectDTO.transformEntityToDTO(entity)).toList();
    }



}
