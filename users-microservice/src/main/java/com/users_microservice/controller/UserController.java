package com.users_microservice.controller;

import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.dto.UpdateUserDTO;
import com.users_microservice.dto.UserDTO;
import com.users_microservice.service.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid RegisAndLogDTO userData) {
        return ResponseEntity.ok(userService.createUser(userData));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long userId, @RequestBody @Valid UpdateUserDTO userData) {
        return ResponseEntity.ok(userService.updateUser(userId, userData));
    }

    @GetMapping("/exists-user/{userId}")
    public ResponseEntity<Boolean> existsUser(@PathVariable long userId) {
        boolean existsUser = userService.userExists(userId);

        if (existsUser) {
            return new ResponseEntity<>(true, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
