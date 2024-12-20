package com.users_microservice.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserDTO(
        Long id,
        String email,
        Set<RoleDTO> roles
) {
}
