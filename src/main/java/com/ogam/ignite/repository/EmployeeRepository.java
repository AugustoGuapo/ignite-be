package com.ogam.ignite.repository;

import com.ogam.ignite.domain.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
}
