package com.auth_microservice.http.out;

import lombok.Builder;

import java.util.List;

@Builder
public record UserTokenDTO(
        String token,
        String email,
        List<String> roles,
        String message,
        boolean success
) {
}
