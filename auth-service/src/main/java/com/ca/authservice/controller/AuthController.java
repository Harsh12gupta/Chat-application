package com.ca.authservice.controller;

import com.ca.authservice.dto.LoginRequestDTO;
import com.ca.authservice.dto.LoginResponseDTO;
import com.ca.authservice.dto.RegisterRequestDTO;
import com.ca.authservice.dto.RegisterResponseDTO;
import com.ca.authservice.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        RegisterResponseDTO registerResponseDTO = authService.register(registerRequestDTO);
        return ResponseEntity.ok().body(registerResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.ok().body(loginResponseDTO);
    }
}
