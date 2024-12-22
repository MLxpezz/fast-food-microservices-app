package com.users_microservice.service.implementation;

import com.users_microservice.entities.PermissionEntity;
import com.users_microservice.entities.RoleEntity;
import com.users_microservice.enums.PermissionEnum;
import com.users_microservice.enums.RoleEnum;
import com.users_microservice.repository.RoleRepository;
import com.users_microservice.service.interfaces.IPermissionService;
import com.users_microservice.service.interfaces.IRoleService;
import com.users_microservice.utils.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final IPermissionService permissionService;

    public RoleServiceImpl(RoleRepository roleRepository, IPermissionService permissionService) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
    }

    @Override
    public void createRole(RoleEnum role, Set<PermissionEntity> permissions) {
        roleRepository.save(RoleMapper.creationRole(role, permissions));
    }

    @Override
    public RoleEntity getRole(RoleEnum role) {
        return roleRepository.findByRole(role).orElseGet(() -> roleRepository.save(RoleEntity
                .builder()
                .role(role)
                .permissions(defaultPermissionsForRole(role))
                .build()));
    }

    @Override
    public Set<PermissionEntity> defaultPermissionsForRole(RoleEnum role) {

        Map<RoleEnum, Set<PermissionEnum>> permissionsForRole = Map.of(
                RoleEnum.ADMIN, Set.of(
                        PermissionEnum.CREATE,
                        PermissionEnum.READ,
                        PermissionEnum.DELETE,
                        PermissionEnum.UPDATE,
                        PermissionEnum.WRITE
                ),
                RoleEnum.EMPLOYEE, Set.of(
                        PermissionEnum.READ,
                        PermissionEnum.WRITE,
                        PermissionEnum.UPDATE,
                        PermissionEnum.CREATE
                ),
                RoleEnum.CUSTOMER, Set.of(
                        PermissionEnum.READ,
                        PermissionEnum.WRITE
                )
        );

        return permissionsForRole
                .get(role)
                .stream()
                .map(permissionService::getPermission)
                .collect(Collectors.toSet());
    }
}
