package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.InvalidUserDataException;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.Role;
import com.tecsup.example.hexagonal.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(User newUser) {

        // Validation logic can be added here
        validateUserInput(newUser);

        // Set default values
        if (newUser.getRole() == null) {
            newUser.setRole(Role.USER);
        }

        // Set default password for new users
        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            String defaultPassword = "password"; // Default password
            newUser.setPassword(passwordEncoder.encode(defaultPassword));
        }

        // Set user as enabled by default
        newUser.setEnabled(true);

        // Note: Role is now set by the mapper based on UserRequest.role field

        // Save the user using the repository
        User user = this.userRepository.save(newUser);

        //user.setName("Margot"); // Garbage line for testing purposes

        return user;

    }



    @Override
    public User findUser(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        User user = this.userRepository.findById(id)
                .orElseThrow( ()-> new UserNotFoundException(id) );

        return user;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByApellidoPaterno(String apellidoPaterno) {
        return this.userRepository.findByApellidoPaterno(apellidoPaterno);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByDni(String dni) {
        return this.userRepository.findByDni(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByEdadLessThan(Integer edad) {
        return this.userRepository.findByEdadLessThan(edad);
    }

    private void validateUserInput(User newUser) {

        if (!newUser.hasValidName())
            throw new InvalidUserDataException("Invalid name");

        if (!newUser.hasValidApellidoPaterno())
            throw new InvalidUserDataException("Invalid apellido paterno");

        if (!newUser.hasValidApellidoMaterno())
            throw new InvalidUserDataException("Invalid apellido materno");

        if (!newUser.hasValidEdad())
            throw new InvalidUserDataException("Invalid edad");

        if (!newUser.hasValidDni())
            throw new InvalidUserDataException("Invalid dni");

        if (!newUser.hasValidNumeroTelefonico())
            throw new InvalidUserDataException("Invalid numero telefonico");

        if (!newUser.hasValidEmail())
            throw new InvalidUserDataException("Invalid email");


    }
}
