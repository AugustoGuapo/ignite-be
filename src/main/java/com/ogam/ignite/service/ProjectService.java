package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import com.ogam.ignite.domain.entities.Project;
import com.ogam.ignite.domain.requests.AddProjectRequest;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.repository.ClientRepository;
import com.ogam.ignite.repository.ProjectRepository;
import com.ogam.ignite.utils.Utils;
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
        Project toSave = project.getProjectId() == null ? Project.transformRequestToEntity(project)
                : projectRepository.findById(project.getProjectId()).orElseThrow(RuntimeException::new);
        if(project.getProjectId() != null) {
            toSave.setCost(Utils.updateField(toSave.getCost(), project.getCost()));
            toSave.setName(Utils.updateField(toSave.getName(), project.getName()));
            toSave.setDescription(Utils.updateField(toSave.getDescription(), project.getDescription()));
            toSave.setDeliveryDate(Utils.updateField(toSave.getDeliveryDate(), project.getDeliveryDate()));
        }
        toSave.setClient(clientRepository.findById(project.getClientId()).orElseThrow(()
                -> new DataNotFoundException(String.format("Client not found for id: %d", project.getClientId()))));
        return ProjectDTO.transformEntityToDTO(projectRepository.save(toSave));
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream().map(entity -> ProjectDTO.transformEntityToDTO(entity)).toList();
    }

}
