package com.food_microservice.service.implementation;

import com.food_microservice.dto.FoodPackageDTO;
import com.food_microservice.entity.FoodPackageEntity;
import com.food_microservice.repository.FoodPackageRepository;
import com.food_microservice.service.interfaces.IFoodPackageService;
import com.food_microservice.utils.FoodMapper;
import com.food_microservice.utils.FoodPackageMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodPackageImpl implements IFoodPackageService {

    private final FoodPackageRepository foodPackageRepository;
    private final FoodPackageMapper foodPackageMapper;
    private final FoodMapper foodMapper;

    public FoodPackageImpl(FoodPackageRepository foodPackageRepository, FoodPackageMapper foodPackageMapper, FoodMapper foodMapper) {
        this.foodPackageRepository = foodPackageRepository;
        this.foodPackageMapper = foodPackageMapper;
        this.foodMapper = foodMapper;
    }


    @Override
    public void createFoodPackage(FoodPackageDTO foodPackageDTO) {
        foodPackageRepository.save(foodPackageMapper.dtoToFoodPackage(foodPackageDTO));
    }

    @Transactional
    @Override
    public void updateFoodPackage(Long id, FoodPackageDTO foodPackageDTO) {
        FoodPackageEntity foodPackage = foodPackageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Package with id " + id + " not found"));

        foodPackage.setName(foodPackageDTO.name());
        foodPackage.setDescription(foodPackageDTO.description());
        foodPackage.setPrice(foodPackageDTO.price());
        foodPackage.setFoods(foodMapper.dtoListToFoodEntityList(foodPackageDTO.foods()));
    }

    @Override
    public void deleteFoodPackage(Long id) {
        FoodPackageEntity foodPackage = foodPackageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Package with id " + id + " not found"));

        foodPackageRepository.delete(foodPackage);
    }

    @Override
    public FoodPackageDTO getFoodPackage(Long id) {
        FoodPackageEntity foodPackage = foodPackageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Package with id " + id + " not found"));

        return foodPackageMapper.foodPackageEntityToDto(foodPackage);
    }

    @Override
    public List<FoodPackageDTO> getFoodPackages() {
        return foodPackageMapper.entityListToDtoList(foodPackageRepository.findAll());
    }
}
