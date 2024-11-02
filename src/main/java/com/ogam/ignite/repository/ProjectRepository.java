package com.ogam.ignite.repository;

import com.ogam.ignite.domain.dtos.ProjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectDTO, Long> {
}
