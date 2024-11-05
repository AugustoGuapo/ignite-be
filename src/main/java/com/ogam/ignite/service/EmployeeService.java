package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.EmployeeDTO;
import com.ogam.ignite.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(entity -> EmployeeDTO.transformEntityToDTO(entity)).toList();
    }
}
