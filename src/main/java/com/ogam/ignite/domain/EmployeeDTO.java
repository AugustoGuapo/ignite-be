package com.ogam.ignite.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class EmployeeDTO {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column
    String name;
    @Column
    String email;
    @Column
    String password;
    @Column
    String cellphoneNumber;
    @Column
    LocalDateTime startDate;
    @Column
    List<String> roles;
}
