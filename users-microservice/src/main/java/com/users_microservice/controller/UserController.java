package com.users_microservice.controller;

import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.dto.UserDTO;
import com.users_microservice.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody RegisAndLogDTO userData) {
        return ResponseEntity.ok(userService.createUser(userData));
    }
}
