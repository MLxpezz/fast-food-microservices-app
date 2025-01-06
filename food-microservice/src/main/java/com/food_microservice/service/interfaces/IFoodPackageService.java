package com.food_microservice.service.interfaces;

import com.food_microservice.dto.FoodPackageDTO;

import java.util.List;

public interface IFoodPackageService {

    void createFoodPackage(FoodPackageDTO foodPackageDTO);

    void updateFoodPackage(Long id, FoodPackageDTO foodPackageDTO);

    void deleteFoodPackage(Long id);

    FoodPackageDTO getFoodPackage(Long id);

    List<FoodPackageDTO> getFoodPackages();
}
