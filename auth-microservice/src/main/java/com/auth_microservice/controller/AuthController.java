package com.auth_microservice.controller;

import com.auth_microservice.http.in.UserDTO;
import com.auth_microservice.http.out.UserTokenDTO;
import com.auth_microservice.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtils jwtUtils;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody UserDTO user) {
        String token = jwtUtils.generateToken(user);
        UserTokenDTO userToken = UserTokenDTO
                .builder()
                .email(user.email())
                .token(token)
                .message("Autenticacion correcta, token generado")
                .success(true)
                .roles(user.roles().stream().toList())
                .build();
        return new ResponseEntity<>(userToken, HttpStatus.OK);
    }
}
