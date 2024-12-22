package com.users_microservice.aspects;

import com.users_microservice.dto.UserDTO;
import com.users_microservice.enums.RoleEnum;
import com.users_microservice.repository.UserRepository;
import com.users_microservice.service.interfaces.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Aspect
@Slf4j
@Component
public class RoleAssignmentAspect {

    private final UserRepository userRepository;
    private final IRoleService roleService;

    public RoleAssignmentAspect(UserRepository userRepository, IRoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @AfterReturning(
            value = "execution(* com.users_microservice.service.interfaces.IUserService.createUser(..))",
            returning = "userDTO"
    )
    @Transactional
    public void roleAssignmentAfterCreation(JoinPoint joinPoint, UserDTO userDTO) {

        log.info("Role assignment entrance method called");

        Long userId = userDTO.id();

        userRepository.findById(userId).ifPresent(user -> {
           user.setRoles(new HashSet<>(Set.of(roleService.getRole(RoleEnum.EMPLOYEE))));
           userRepository.save(user);
        });

        log.info("Role assignment after creation successfully");
    }
}
