package com.users_microservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisAndLogDTO(
        @Email(message = "El email es invalido")
        @NotBlank(message = "El campo es requerido")
        String email,
        @Size(min = 8, message = "Debe tener 8 caracteres minimo")
        @NotBlank(message = "El campo es requerido")
        String password
) {
}
