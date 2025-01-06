package com.food_microservice.service.implementation;

import com.food_microservice.dto.FoodDTO;
import com.food_microservice.entity.FoodEntity;
import com.food_microservice.repository.FoodRepository;
import com.food_microservice.service.interfaces.IFoodService;
import com.food_microservice.utils.FoodMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodServiceImpl implements IFoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public FoodServiceImpl(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }

    @Override
    public void createFood(FoodDTO food) {
        foodRepository.save(foodMapper.dtoToFoodEntity(food));
    }

    @Transactional
    @Override
    public void updateFood(Long id, FoodDTO food) {
        FoodEntity foodEntity = foodRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food with id: " + id + "not found"));

        foodEntity.setName(food.name());
        foodEntity.setDescription(food.description());
        foodEntity.setPrice(food.price());
    }

    @Override
    public void deleteFood(Long id) {
        FoodEntity foodEntity = foodRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food with id: " + id + "not found"));

        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDTO> getFoods() {
        return foodMapper.foodEntityToDtoList(foodRepository.findAll());
    }

    @Override
    public FoodDTO getFood(Long id) {
        FoodEntity foodEntity = foodRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food with id: " + id + "not found"));

        return foodMapper.foodEntityToDto(foodEntity);
    }
}
