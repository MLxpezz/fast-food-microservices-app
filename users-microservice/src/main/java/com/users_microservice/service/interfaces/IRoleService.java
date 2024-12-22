package com.users_microservice.service.interfaces;

import com.users_microservice.entities.PermissionEntity;
import com.users_microservice.entities.RoleEntity;
import com.users_microservice.enums.RoleEnum;

import java.util.Set;

public interface IRoleService {

    void createRole(RoleEnum role, Set<PermissionEntity> permissions);

    RoleEntity getRole(RoleEnum role);

    Set<PermissionEntity> defaultPermissionsForRole(RoleEnum role);
}
