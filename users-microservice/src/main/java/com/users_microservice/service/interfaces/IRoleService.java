package com.users_microservice.service.interfaces;

import com.users_microservice.entities.RoleEntity;
import com.users_microservice.enums.RoleEnum;

public interface IRoleService {

    void createRole(RoleEnum role);

    RoleEntity getRole(RoleEnum role);
}
