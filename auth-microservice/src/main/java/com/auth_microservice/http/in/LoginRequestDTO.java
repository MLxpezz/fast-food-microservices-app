package com.auth_microservice.http.in;

import lombok.Builder;

@Builder
public record LoginRequestDTO (
        String email,
        String password
) {
}
