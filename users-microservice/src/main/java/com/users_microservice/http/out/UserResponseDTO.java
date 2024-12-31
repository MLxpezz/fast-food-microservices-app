package com.users_microservice.http.out;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponseDTO(
        Long id,
        String email,
        List<String> roles
) {
}
