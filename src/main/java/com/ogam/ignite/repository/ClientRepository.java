package com.ogam.ignite.repository;


import com.ogam.ignite.domain.dtos.ClientDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientDTO, Long> {
}