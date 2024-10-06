package com.ogam.ignite.controller;

import com.ogam.ignite.domain.CredentialsDTO;
import com.ogam.ignite.domain.LoginResponse;
import com.ogam.ignite.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> login(CredentialsDTO credentials) {
        return ResponseEntity.ok(loginService.login(credentials));
    }
}
