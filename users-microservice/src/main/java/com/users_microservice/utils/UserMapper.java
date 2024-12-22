package com.users_microservice.utils;

import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.dto.RoleDTO;
import com.users_microservice.dto.UserDTO;
import com.users_microservice.entities.UserEntity;

import java.util.Collections;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity createUserFromRegisRequest(RegisAndLogDTO user) {
        return UserEntity
                .builder()
                .email(user.email())
                .password(user.password())
                .build();
    }

    public static UserDTO dtoFromEntity(UserEntity user) {
        return UserDTO
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

}
