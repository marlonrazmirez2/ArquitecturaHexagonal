package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.AuthService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.AuthResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse login(String email, String password) {

        // Validate data
        validateData(email, password);

        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        // Is user enabled?
        if (!user.isEnabled())
            throw new IllegalArgumentException("User account is disabled");


        // Check password
        String passwordEncrypted = user.getPassword();

        if (!passwordEncoder.matches(password, passwordEncrypted)) {
            throw new IllegalArgumentException("Invalid email or password");
        }


        // Create AuthResponse
        AuthResponse authResponse = new AuthResponse();
//        authResponse.setUserId(user.getId());
//        authResponse.setEmail(user.getEmail());
        authResponse.setToken(generateToken(user));

        return authResponse;
    }

    @Override
    public String generateToken(User user) {
        return jwtTokenProvider.generateToken(user);
    }


    private void validateData(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
    }

}
