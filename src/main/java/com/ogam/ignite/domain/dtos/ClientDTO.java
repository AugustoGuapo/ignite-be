package com.ogam.ignite.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ogam.ignite.domain.entities.Client;
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
public class ClientDTO {

    private Long id;
    private String name;
    private String cellphoneNumber;
    private String email;
    private LocalDateTime createdAt;
    private List<Long> projects;

    public static ClientDTO transformEntityToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .cellphoneNumber(client.getCellphoneNumber())
                .email(client.getEmail())
                .createdAt(client.getCreatedAt())
                .projects(client.getProjects() == null ? null :
                        client.getProjects().stream().map(entity -> entity.getId()).toList())
                .build();
    }
}
