package com.food_microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FoodDTO(
        Long id,
        @NotBlank(message = "El campo es requerido")
        String name,
        @NotBlank(message = "El campo es requerido")
        String description,
        @Min(value = 1, message = "El precio no puede ser 0")
        @NotNull(message = "El campo es requerido")
        Double price,
        @JsonProperty("type_food")
        @NotBlank(message = "El campo es requerido")
        String typeFood
) {
}
