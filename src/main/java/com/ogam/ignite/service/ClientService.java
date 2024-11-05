package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.ClientDTO;
import com.ogam.ignite.domain.entities.Client;
import com.ogam.ignite.domain.requests.AddClientRequest;
import com.ogam.ignite.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO addNewClient(AddClientRequest client) {
        return ClientDTO.transformEntityToDTO(clientRepository.save(Client.transformRequestToEntity(client)));
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(entity -> ClientDTO.transformEntityToDTO(entity)).toList();
    }
}
