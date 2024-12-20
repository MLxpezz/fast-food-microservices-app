package com.users_microservice.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record RoleDTO(
        String role,
        Set<PermissionDTO> permissionList
) {
}
