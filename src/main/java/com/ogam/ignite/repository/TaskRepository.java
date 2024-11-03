package com.ogam.ignite.repository;

import com.ogam.ignite.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
