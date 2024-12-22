package com.users_microservice.service.implementation;

import com.users_microservice.entities.PermissionEntity;
import com.users_microservice.enums.PermissionEnum;
import com.users_microservice.repository.PermissionRepository;
import com.users_microservice.service.interfaces.IPermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void createPermission(PermissionEnum permission) {
        permissionRepository.save(
                PermissionEntity
                        .builder()
                        .permission(permission)
                        .build()
        );
    }

    @Override
    public PermissionEntity getPermission(PermissionEnum permission) {
        return permissionRepository.findByPermission(permission)
                .orElseGet(() -> permissionRepository.save(PermissionEntity.builder().permission(permission).build()));
    }
}
