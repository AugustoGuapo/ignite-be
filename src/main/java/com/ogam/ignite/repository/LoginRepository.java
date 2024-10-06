package com.ogam.ignite.repository;

import com.ogam.ignite.domain.EmployeeDTO;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface LoginRepository extends Repository<EmployeeDTO, Long> {

    Optional<EmployeeDTO> findByEmailAndPassword(String email, String password);
    Optional<EmployeeDTO> findByEmail(String email);
}
