package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.controller;

import com.tecsup.example.hexagonal.application.port.input.AuthService;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.AuthResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.LoginRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;


    /**
     *
     * @param request
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());
        log.info("Login attempt for password: {}", request.getPassword());
        try{
            AuthResponse authResponse = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            log.error("Error during login: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
