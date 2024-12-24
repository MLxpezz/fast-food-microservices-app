package com.users_microservice.utils;

import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.dto.RoleDTO;
import com.users_microservice.dto.UserDTO;
import com.users_microservice.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntity createUserFromRegisRequest(RegisAndLogDTO user) {
        return UserEntity
                .builder()
                .email(user.email())
                .password(user.password())
                .build();
    }

    public UserDTO dtoFromEntity(UserEntity user) {
        return UserDTO
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public List<UserDTO> dtoListFromEntities(List<UserEntity> users) {
        return users
                .stream()
                .map(this::dtoFromEntity)
                .toList();
    }

}
