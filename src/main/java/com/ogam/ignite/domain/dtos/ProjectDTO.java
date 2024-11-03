package com.ogam.ignite.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ogam.ignite.domain.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectDTO {
    Long id;
    ClientDTO client;
    String name;
    Double cost;
    LocalDateTime createdAt;
    LocalDateTime deliveryDate;
    List<TaskDTO> tasks;

    public static ProjectDTO transformEntityToDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .client(ClientDTO.transformEntityToDTO(project.getClient()))
                .cost(project.getCost())
                .name(project.getName())
                .createdAt(LocalDateTime.now())
                .deliveryDate(project.getDeliveryDate())
                .tasks(project.getTasks() == null ? null :
                        project.getTasks().stream().map(entity -> TaskDTO.transformEntityToDTO(entity)).toList())
                .build();
    }
}
