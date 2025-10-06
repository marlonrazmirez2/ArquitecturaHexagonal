package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.controller;


import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.exception.InvalidUserDataException;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@RequestBody(required = false) UserRequest request) {
        try {

            if (request == null) {
                log.warn("Received null UserRequest");
                return ResponseEntity.badRequest().build();
            }

            log.info("Creating request with name: {} and email: {}", request.getName(), request.getEmail());
            User newUser = this.userMapper.toDomain(request);

            // Set role based on request
            if (request.getRole() != null) {
                switch (request.getRole().toUpperCase()) {
                    case "ADMIN":
                        newUser.setRole(com.tecsup.example.hexagonal.domain.model.Role.ADMIN);
                        break;
                    case "MONITOR":
                        newUser.setRole(com.tecsup.example.hexagonal.domain.model.Role.MONITOR);
                        break;
                    case "USER":
                    default:
                        newUser.setRole(com.tecsup.example.hexagonal.domain.model.Role.USER);
                        break;
                }
            }

            log.info("Mapped User entity: {}", newUser);
            User createUser = this.userService.createUser(newUser);

            UserResponse response = this.userMapper.toResponse(createUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (InvalidUserDataException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        try {

            User user = this.userService.findUser(id);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            log.warn("User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", id, e);
            return ResponseEntity.badRequest().build();

        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/apellido-paterno")
    public ResponseEntity<List<UserResponse>> searchByApellidoPaterno(@RequestParam String apellidoPaterno) {
        try {
            List<User> users = this.userService.findUsersByApellidoPaterno(apellidoPaterno);
            List<UserResponse> responses = users.stream()
                    .map(this.userMapper::toResponse)
                    .collect(java.util.stream.Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error("Error searching users by apellido paterno: {}", apellidoPaterno, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/dni")
    public ResponseEntity<List<UserResponse>> searchByDni(@RequestParam String dni) {
        try {
            List<User> users = this.userService.findUsersByDni(dni);
            List<UserResponse> responses = users.stream()
                    .map(this.userMapper::toResponse)
                    .collect(java.util.stream.Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error("Error searching users by dni: {}", dni, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('MONITOR')")
    @GetMapping("/search/edad-less-than")
    public ResponseEntity<List<UserResponse>> searchByEdadLessThan(@RequestParam Integer edad) {
        try {
            List<User> users = this.userService.findUsersByEdadLessThan(edad);
            List<UserResponse> responses = users.stream()
                    .map(this.userMapper::toResponse)
                    .collect(java.util.stream.Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error("Error searching users by edad less than: {}", edad, e);
            return ResponseEntity.badRequest().build();
        }
    }


}





























