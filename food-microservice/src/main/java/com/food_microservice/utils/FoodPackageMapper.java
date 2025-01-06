package com.food_microservice.utils;

import com.food_microservice.dto.FoodDTO;
import com.food_microservice.dto.FoodPackageDTO;
import com.food_microservice.entity.FoodEntity;
import com.food_microservice.entity.FoodPackageEntity;
import com.food_microservice.enums.TypeFoodEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodPackageMapper {

    public FoodPackageDTO foodPackageEntityToDto(FoodPackageEntity foodPackage) {
        return FoodPackageDTO
                .builder()
                .id(foodPackage.getId())
                .name(foodPackage.getName())
                .description(foodPackage.getDescription())
                .price(foodPackage.getPrice())
                .foods(
                        foodPackage.getFoods()
                                .stream().map(food -> {
                                    return FoodDTO
                                            .builder()
                                            .id(food.getId())
                                            .name(food.getName())
                                            .description(food.getDescription())
                                            .price(food.getPrice())
                                            .typeFood(food.getTypeFood().name())
                                            .build();
                                })
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public FoodPackageEntity dtoToFoodPackage(FoodPackageDTO foodPackageDTO) {
        return FoodPackageEntity
                .builder()
                .name(foodPackageDTO.name())
                .description(foodPackageDTO.description())
                .price(foodPackageDTO.price())
                .foods(
                        foodPackageDTO.foods()
                                .stream().map(food -> {
                                    return FoodEntity
                                            .builder()
                                            .id(food.id())
                                            .name(food.name())
                                            .description(food.description())
                                            .price(food.price())
                                            .typeFood(TypeFoodEnum.fromString(food.typeFood()))
                                            .build();
                                })
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public List<FoodPackageDTO> entityListToDtoList(List<FoodPackageEntity> foodEntities) {
        return foodEntities
                .stream()
                .map(this::foodPackageEntityToDto)
                .toList();
    }

    public List<FoodPackageEntity> dtoListToEntityList(List<FoodPackageDTO> foodPackageDTOs) {
        return foodPackageDTOs
                .stream()
                .map(this::dtoToFoodPackage)
                .toList();
    }
}
