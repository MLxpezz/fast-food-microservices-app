package com.food_microservice.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record FoodPackageDTO(
        Long id,
        String name,
        String description,
        Double price,
        Set<FoodDTO> foods
) {
}
