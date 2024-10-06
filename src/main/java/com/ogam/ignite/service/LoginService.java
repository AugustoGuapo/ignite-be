package com.ogam.ignite.service;

import com.ogam.ignite.domain.CredentialsDTO;
import com.ogam.ignite.domain.EmployeeDTO;
import com.ogam.ignite.domain.LoginResponse;
import com.ogam.ignite.exceptions.DataNotFoundException;
import com.ogam.ignite.exceptions.InvalidPasswordException;
import com.ogam.ignite.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LoginService {

    LoginRepository loginRepository;
    PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(CredentialsDTO credentials) {
        Optional<EmployeeDTO> employee;
        employee = loginRepository.findByEmail(credentials.getEmail());
        if(employee.isEmpty()) {
            throw new DataNotFoundException("Email and password combination is not valid");
        }
        if(!passwordEncoder.matches(credentials.getPassword(), employee.get().getPassword())) {
            throw new InvalidPasswordException("Invalid password. Access denied");
        }
        return new LoginResponse("token de mentira");
    }
}
