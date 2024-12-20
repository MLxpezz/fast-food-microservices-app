package com.users_microservice.repository;

import com.users_microservice.entities.PermissionEntity;
import com.users_microservice.enums.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findByPermission(PermissionEnum permission);
}
