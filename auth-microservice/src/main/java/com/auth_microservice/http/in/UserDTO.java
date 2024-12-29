package com.auth_microservice.http.in;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserDTO (
        Long id,
        String email,
        Set<String> roles
) {
}
