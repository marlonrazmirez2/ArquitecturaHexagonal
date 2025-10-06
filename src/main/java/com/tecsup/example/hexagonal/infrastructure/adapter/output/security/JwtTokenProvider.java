package com.tecsup.example.hexagonal.infrastructure.adapter.output.security;


import com.tecsup.example.hexagonal.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecretString;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationMs;

    /**
     * Generate token
     * @param user
     * @return
     */
    public String generateToken(User user) {
        // Aquí iría la lógica para crear un token JWT real

        log.info("Creating token for email: {}", user.getEmail());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs); //

        return Jwts.builder()
                .subject(user.getEmail())  // .setSubject(user.getUsername())
                .claim("email", user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole().getName())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();

    }

    /**
     * Get signing key
     * @return
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecretString);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            log.error("Invalid JWT token: {}", ex.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Collection<? extends GrantedAuthority> getRoleFromToken(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        // ADMIN, USER
        // ROLE_ADMIN, ROLE_USER
        String role = "ROLE_" + claims.get("role", String.class);
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
