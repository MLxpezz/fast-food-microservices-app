package com.users_microservice.utils;

import com.users_microservice.entities.PermissionEntity;
import com.users_microservice.entities.RoleEntity;
import com.users_microservice.enums.RoleEnum;

import java.util.Set;

public class RoleMapper {

    public static RoleEntity creationRole(RoleEnum role, Set<PermissionEntity> permissions) {
        return RoleEntity
                .builder()
                .role(role)
                .permissions(permissions)
                .build();
    }
}
