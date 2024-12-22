package com.users_microservice.service.implementation;

import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.dto.UpdateUserDTO;
import com.users_microservice.dto.UserDTO;
import com.users_microservice.entities.UserEntity;
import com.users_microservice.repository.UserRepository;
import com.users_microservice.service.interfaces.IUserService;
import com.users_microservice.utils.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDTO createUser(RegisAndLogDTO userInfo) {
        UserEntity newUser = userRepository.save(UserMapper.createUserFromRegisRequest(userInfo));
        return UserMapper.dtoFromEntity(newUser);
    }

    @Transactional
    @Override
    public UserDTO updateUser(UpdateUserDTO userInfo) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUser(Long id) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getUsers() {
        return List.of();
    }

    @Transactional
    @Override
    public String deleteUser(Long id) {
        return "";
    }
}
