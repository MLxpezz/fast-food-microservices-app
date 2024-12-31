package com.auth_microservice.service.implementation;

import com.auth_microservice.http.in.UserDTO;
import com.auth_microservice.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UserServiceImpl implements IUserService {

    private final WebClient webClient;

    public UserServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public UserDTO getUser(Long id) {
        try {
            return webClient
                    .get()
                    .uri("/api/v1/customers/check-customer/{id}", id)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block();
        } catch (WebClientResponseException exception) {
                if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new RuntimeException("User with id " + id + " not found");
                }
                throw new RuntimeException("communication error");
        }
    }
}
