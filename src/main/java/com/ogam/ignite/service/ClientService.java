package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.ClientDTO;
import com.ogam.ignite.domain.entities.Client;
import com.ogam.ignite.domain.requests.AddClientRequest;
import com.ogam.ignite.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO addNewClient(AddClientRequest client) {
        return ClientDTO.transformEntityToDTO(clientRepository.save(Client.transformRequestToEntity(client)));
    }
}
