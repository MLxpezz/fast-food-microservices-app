package com.food_microservice.repository;

import com.food_microservice.entity.FoodPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodPackageRepository extends JpaRepository<FoodPackageEntity, Long> {
}
