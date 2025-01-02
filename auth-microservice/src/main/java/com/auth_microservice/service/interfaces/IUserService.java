package com.auth_microservice.service.interfaces;

import com.auth_microservice.http.in.LoginRequestDTO;
import com.auth_microservice.http.in.UserDTO;

public interface IUserService {

    UserDTO getUser(LoginRequestDTO userCredentials);
}
