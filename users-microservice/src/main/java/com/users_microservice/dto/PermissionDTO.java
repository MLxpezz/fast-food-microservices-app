package com.users_microservice.dto;

import lombok.Builder;

@Builder
public record PermissionDTO(
        String permission
) {
}
