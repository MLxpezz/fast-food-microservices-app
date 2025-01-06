package com.food_microservice.utils;

import com.food_microservice.dto.FoodDTO;
import com.food_microservice.entity.FoodEntity;
import com.food_microservice.enums.TypeFoodEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class FoodMapper {

    public FoodEntity dtoToFoodEntity(FoodDTO food) {
        return FoodEntity
                .builder()
                .name(food.name())
                .description(food.description())
                .price(food.price())
                .typeFood(TypeFoodEnum.valueOf(food.typeFood().toUpperCase(Locale.ROOT)))
                .build();
    }

    public FoodDTO foodEntityToDto(FoodEntity food) {
        return FoodDTO
                .builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .typeFood(food.getTypeFood().name())
                .build();
    }

    public List<FoodDTO> foodEntityToDtoList(List<FoodEntity> foods) {
        return foods
                .stream()
                .map(this::foodEntityToDto)
                .collect(Collectors.toList());
    }
}
