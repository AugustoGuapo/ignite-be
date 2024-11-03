package com.ogam.ignite.repository;


import com.ogam.ignite.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
