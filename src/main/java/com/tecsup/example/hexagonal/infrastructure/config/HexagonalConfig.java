package com.tecsup.example.hexagonal.infrastructure.config;

import com.tecsup.example.hexagonal.application.port.input.AuthService;
import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.application.service.AuthServiceImpl;
import com.tecsup.example.hexagonal.application.service.UserServiceImpl;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class HexagonalConfig {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    public AuthService authService(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   JwtTokenProvider jwtTokenProvider) {
        return new AuthServiceImpl(userRepository, passwordEncoder, jwtTokenProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
