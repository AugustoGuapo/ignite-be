package com.ogam.ignite.controller;

import com.ogam.ignite.domain.dtos.ClientDTO;
import com.ogam.ignite.domain.requests.AddClientRequest;
import com.ogam.ignite.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/clients")
    ResponseEntity<ClientDTO> addNewClient(@RequestBody AddClientRequest client) {
        return ResponseEntity.ok(service.addNewClient(client));
    }
}