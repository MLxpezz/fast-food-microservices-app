package com.food_microservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record FoodPackageDTO(
        Long id,
        @NotBlank(message = "El campo es requerido")
        String name,
        @NotBlank(message = "El campo es requerido")
        String description,
        @NotNull(message = "El campo es requerido")
        @Min(value = 1, message = "El precio no puede ser 0")
        Double price,
        @NotNull(message = "El campo es requerido")
        Set<FoodDTO> foods
) {
}
