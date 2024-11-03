package com.ogam.ignite.repository;

import com.ogam.ignite.domain.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
