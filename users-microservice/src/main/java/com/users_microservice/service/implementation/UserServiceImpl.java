package com.users_microservice.service.implementation;

import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.dto.UpdateUserDTO;
import com.users_microservice.dto.UserDTO;
import com.users_microservice.entities.UserEntity;
import com.users_microservice.repository.UserRepository;
import com.users_microservice.service.interfaces.IUserService;
import com.users_microservice.utils.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserDTO createUser(RegisAndLogDTO userInfo) {
        UserEntity newUser = userRepository.save(userMapper.createUserFromRegisRequest(userInfo));
        return userMapper.dtoFromEntity(newUser);
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UpdateUserDTO userInfo) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));

        user.setEmail(userInfo.email());
        user.setPassword(userInfo.password());

        return userMapper.dtoFromEntity(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUser(Long id) {
        return userRepository
                .findById(id)
                .map(userMapper::dtoFromEntity)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getUsers() {
        return userMapper.dtoListFromEntities(userRepository.findAll());
    }

    @Transactional
    @Override
    public String deleteUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
            return "Usuario eliminado exitosamente";
        }

        return "Ocurrio un error, el usuario no existe";
    }
}
