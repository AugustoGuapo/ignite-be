package com.ogam.ignite.domain.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ogam.ignite.domain.requests.AddProjectRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROJECTS")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProjectDTO {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    ClientDTO client;

    @Column
    String name;

    @Column
    Double cost;

    @Column
    LocalDateTime createdAt;

    @Column
    LocalDateTime deliveryDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<TaskDTO> tasks;

    public static ProjectDTO transformRequestToDTO(AddProjectRequest request) {
        ProjectDTO response = new ProjectDTO();

        ClientDTO client = new ClientDTO();
        client.setId(request.getClientId());

        response.client = client;
        response.cost = request.getCost();
        response.createdAt = LocalDateTime.now();
        response.name = request.getName();
        response.deliveryDate = request.getDeliveryDate();

        return response;
    }
}
