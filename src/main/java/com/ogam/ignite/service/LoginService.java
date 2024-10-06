package com.ogam.ignite.service;

import com.ogam.ignite.domain.CredentialsDTO;
import com.ogam.ignite.domain.EmployeeDTO;
import com.ogam.ignite.domain.LoginResponse;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LoginService {

    LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LoginResponse login(CredentialsDTO credentials) {
        Optional<EmployeeDTO> employee = null;
        try {
            employee = loginRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        } catch(Exception e) {
            log.error(e.toString());
        }

        if(Objects.nonNull(employee) && employee.isEmpty()) {
            throw new DataNotFoundException("Email and password combination is not valid");
        }
        return new LoginResponse("token de mentira");
    }
}
