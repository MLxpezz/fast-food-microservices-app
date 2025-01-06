package com.food_microservice.service.interfaces;

import com.food_microservice.dto.FoodDTO;

import java.util.List;

public interface IFoodService {

    void createFood(FoodDTO food);

    void updateFood(Long id, FoodDTO food);

    void deleteFood(Long id);

    List<FoodDTO> getFoods();

    FoodDTO getFood(Long id);
}
