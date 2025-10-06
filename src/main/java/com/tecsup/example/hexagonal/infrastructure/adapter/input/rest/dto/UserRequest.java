package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer edad;
    private String dni;
    private String numeroTelefonico;
    private String email;
    private String role; // ADMIN, MONITOR, USER
}
