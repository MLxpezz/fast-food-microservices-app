package com.users_microservice.service.interfaces;

import com.users_microservice.entities.PermissionEntity;
import com.users_microservice.enums.PermissionEnum;

public interface IPermissionService {

    void createPermission(PermissionEnum permission);

    PermissionEntity getPermission(PermissionEnum permission);
}
