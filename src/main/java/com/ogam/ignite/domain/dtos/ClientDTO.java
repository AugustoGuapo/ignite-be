package com.ogam.ignite.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ogam.ignite.domain.requests.AddClientRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "CLIENTS")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClientDTO {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String cellphoneNumber;

    @Column
    private String email;

    @Column
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProjectDTO> projects;

    public static ClientDTO transformRequestToDTO(AddClientRequest request) {
        ClientDTO response = new ClientDTO();
        response.name = request.getName();
        response.cellphoneNumber = request.getCellphoneNumber();
        response.createdAt = LocalDateTime.now();
        response.email = request.getEmail();
        return response;
    }
}