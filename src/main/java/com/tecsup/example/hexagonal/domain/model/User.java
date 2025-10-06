package com.tecsup.example.hexagonal.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String name;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer edad;
    private String dni;
    private String numeroTelefonico;
    private String email;
    private String password;
    private boolean enabled;

    private Role role;

    // Business logic methods - PURE domain logic!
    public boolean hasValidEmail() {
        return email != null &&
                email.contains("@") &&
                email.contains(".") &&
                email.length() > 5;
    }

    public boolean hasValidName() {
        return name != null &&
                !name.trim().isEmpty() &&
                name.length() >= 2;
    }

    public boolean hasValidApellidoPaterno() {
        return apellidoPaterno != null &&
                !apellidoPaterno.trim().isEmpty() &&
                apellidoPaterno.length() >= 2;
    }

    public boolean hasValidApellidoMaterno() {
        return apellidoMaterno != null &&
                !apellidoMaterno.trim().isEmpty() &&
                apellidoMaterno.length() >= 2;
    }

    public boolean hasValidEdad() {
        return edad != null && edad > 0 && edad <= 120;
    }

    public boolean hasValidDni() {
        return dni != null &&
                dni.matches("\\d{8}") &&
                dni.length() == 8;
    }

    public boolean hasValidNumeroTelefonico() {
        return numeroTelefonico != null &&
                numeroTelefonico.matches("\\d{9}") &&
                numeroTelefonico.length() == 9;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }

}
