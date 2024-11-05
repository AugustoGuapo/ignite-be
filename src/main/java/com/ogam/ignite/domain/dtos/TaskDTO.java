package com.ogam.ignite.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ogam.ignite.domain.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TaskDTO {
    private Long id;
    private String name;
    private String grade;
    private Long assignee;
    private Float cost;
    private Long project;
    private String description;
    private Integer status;

    public static TaskDTO transformEntityToDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .cost(task.getPrice())
                .grade(task.getGrade())
                .name(task.getName())
                .assignee(task.getAssignee() == null ? null : task.getAssignee().getId())
                .project(task.getProject() == null ? null : task.getProject().getId())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}
