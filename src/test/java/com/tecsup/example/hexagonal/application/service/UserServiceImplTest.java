package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        // Aquí puedes inicializar los mocks y el servicio
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void createUser() {

        Long ID = 50L;
        String NAME = "Juana";
        String APELLIDO_PATERNO = "Garcia";
        String APELLIDO_MATERNO = "Lopez";
        Integer EDAD = 25;
        String DNI = "12345678";
        String NUMERO_TELEFONICO = "987654321";
        String EMAIL = "juana@demo.com";

        // Initial Condition
        User newUser = User.builder()
                .name(NAME)
                .apellidoPaterno(APELLIDO_PATERNO)
                .apellidoMaterno(APELLIDO_MATERNO)
                .edad(EDAD)
                .dni(DNI)
                .numeroTelefonico(NUMERO_TELEFONICO)
                .email(EMAIL)
                .build();

        User savedUser = User.builder()
                .id(ID)
                .name(NAME)
                .apellidoPaterno(APELLIDO_PATERNO)
                .apellidoMaterno(APELLIDO_MATERNO)
                .edad(EDAD)
                .dni(DNI)
                .numeroTelefonico(NUMERO_TELEFONICO)
                .email(EMAIL)
                .build();

        // Mocking the repository behavior
        when(userRepository.save(newUser)).thenReturn(savedUser);

        // Execute the service method
        User realUser = userService.createUser(newUser);

        // Validate the results
        assertNotNull(realUser);
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(APELLIDO_PATERNO, realUser.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO, realUser.getApellidoMaterno());
        assertEquals(EDAD, realUser.getEdad());
        assertEquals(DNI, realUser.getDni());
        assertEquals(NUMERO_TELEFONICO, realUser.getNumeroTelefonico());
        assertEquals(EMAIL, realUser.getEmail());

    }

    @Test
    void findUser() {
        Long ID = 100L;
        String NAME = "Jaime";
        String APELLIDO_PATERNO = "Rodriguez";
        String APELLIDO_MATERNO = "Martinez";
        Integer EDAD = 30;
        String DNI = "87654321";
        String NUMERO_TELEFONICO = "987654322";
        String EMAIL = "jaime@demo.com";

        // Initial Condition
        User existingUser = User.builder()
                            .id(ID)
                            .name(NAME)
                            .apellidoPaterno(APELLIDO_PATERNO)
                            .apellidoMaterno(APELLIDO_MATERNO)
                            .edad(EDAD)
                            .dni(DNI)
                            .numeroTelefonico(NUMERO_TELEFONICO)
                            .email(EMAIL)
                            .build();

        // Mocking the repository behavior
        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));

        // Execute the service method
        User realUser = userService.findUser(100L);

        // Validate the results
        assertNotNull(realUser);

        // hope values, real values
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(APELLIDO_PATERNO, realUser.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO, realUser.getApellidoMaterno());
        assertEquals(EDAD, realUser.getEdad());
        assertEquals(DNI, realUser.getDni());
        assertEquals(NUMERO_TELEFONICO, realUser.getNumeroTelefonico());
        assertEquals(EMAIL, realUser.getEmail());

    }

    @Test
    public void findUser_NotFound() {
        Long ID_UNKNOW = 999L;

        // Mocking the repository behavior to return empty
        when(userRepository.findById(ID_UNKNOW)).thenReturn(Optional.empty());

        // Execute the service method and expect an exception
        assertThrows(UserNotFoundException.class,
                () -> userService.findUser(ID_UNKNOW));

    }

}













