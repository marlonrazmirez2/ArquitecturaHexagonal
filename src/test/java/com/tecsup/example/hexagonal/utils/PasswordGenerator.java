package com.tecsup.example.hexagonal.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String passwordRaw = "nose123";
        String passwordEncoded = encoder.encode(passwordRaw);
        //log.info("{} -> {}", passwordRaw, passwordEncoded);
        System.out.println(passwordEncoded);
    }
}
