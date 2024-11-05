package com.ogam.ignite.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ogam.ignite.domain.requests.AddTaskRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "TASKS")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String grade;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Employee assignee;

    @Column
    private Float price;

    @Column
    private String description;

    @Column
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public static Task transformRequestToEntity(AddTaskRequest request) {
        Project project = new Project();
        project.setId(request.getProjectId());
        return Task.builder()
                .id(request.getTaskId())
                .price(request.getPrice())
                .grade(request.getGrade())
                .name(request.getName())
                .description(request.getDescription())
                .project(project)
                .assignee(Employee.builder().id(request.getEmployeeId()).build())
                .status(1)
                .build();
    }
}
