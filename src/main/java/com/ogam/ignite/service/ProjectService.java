package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.requests.AddProjectRequest;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.repository.ClientRepository;
import com.ogam.ignite.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private ClientRepository clientRepository;

    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
    }

    public ProjectDTO addNewProject(AddProjectRequest project) {
        ProjectDTO toSave = ProjectDTO.transformRequestToDTO(project);
        toSave.setClient(clientRepository.findById(project.getClientId()).orElseThrow(()
                -> new DataNotFoundException(String.format("Client not found for id: %d", project.getClientId()))));
        return projectRepository.save(toSave);
    }
}
