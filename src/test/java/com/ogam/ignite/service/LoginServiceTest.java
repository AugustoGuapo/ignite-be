package com.ogam.ignite.service;

import com.ogam.ignite.domain.dtos.CredentialsDTO;
import com.ogam.ignite.domain.dtos.EmployeeDTO;
import com.ogam.ignite.repository.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    LoginRepository loginRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    LoginService loginService;

    @BeforeEach
    void setup() {
        loginService = new LoginService(loginRepository, passwordEncoder);
    }

    @Test
    void ShouldLoginSuccessful() {
        CredentialsDTO credentials = new CredentialsDTO("a", "a");
        EmployeeDTO employee = EmployeeDTO.

        when(loginRepository.findByEmail(any())).thenReturn()
    }
}