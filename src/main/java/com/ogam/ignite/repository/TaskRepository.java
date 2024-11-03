package com.ogam.ignite.repository;

import com.ogam.ignite.domain.dtos.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskDTO, Long> {
}
