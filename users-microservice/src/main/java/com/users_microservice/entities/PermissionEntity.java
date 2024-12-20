package com.users_microservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.security.Permission;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false, unique = true)
    private Permission permission;
}
