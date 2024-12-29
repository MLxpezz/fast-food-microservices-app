package com.auth_microservice.utils;

import com.auth_microservice.http.in.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    //private final String SECRET_KEY = System.getenv("JWT_SECRET_KEY");

    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public String generateToken(UserDTO user) {

        Instant now = Instant.now();
        String EXPIRATION_TIME = "86400000";
        Instant expirationInstant = now.plus(Long.parseLong(EXPIRATION_TIME), ChronoUnit.MILLIS);

        List<String> authorities = user.roles()
                .stream()
                .toList();

        String claims = String.join(",", user.roles());

        return Jwts
                .builder()
                .signWith(SECRET_KEY)
                .expiration(Date.from(expirationInstant))
                .issuedAt(Date.from(now))
                .claim("authorities", claims)
                .subject(user.email())
                .compact();
    }

    public boolean validateToken(String token, UserDTO user) {
        if (token == null || token.isEmpty()) {
            throw new JwtException("Token invalido");
        }

        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject()
                    .equals(user.email());
        } catch (JwtException e) {
            throw new JwtException("Token invalido");
        }
    }

    public String emailFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /*public Key signingKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }*/
}
