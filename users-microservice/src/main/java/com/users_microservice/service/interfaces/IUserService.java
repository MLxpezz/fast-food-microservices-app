package com.users_microservice.service.interfaces;

import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.dto.UpdateUserDTO;
import com.users_microservice.dto.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO createUser(RegisAndLogDTO userInfo);

    UserDTO updateUser(UpdateUserDTO userInfo);

    UserDTO getUser(Long id);

    List<UserDTO> getUsers();

    String deleteUser(Long id);
}
