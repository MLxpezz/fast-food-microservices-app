package com.food_microservice.controller;

import com.food_microservice.dto.FoodDTO;
import com.food_microservice.service.interfaces.IFoodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private final IFoodService foodService;

    public FoodController(IFoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFood(@RequestBody @Valid FoodDTO food) {
        foodService.createFood(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<FoodDTO>> getAllFoods() {
        return new ResponseEntity<>(foodService.getFoods(), HttpStatus.OK);
    }

    @GetMapping("/get/{foodId}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable long foodId) {
        return new ResponseEntity<>(foodService.getFood(foodId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{foodId}")
    public ResponseEntity<?> deleteFood(@PathVariable long foodId) {
        foodService.deleteFood(foodId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{foodId}")
    public ResponseEntity<?> updateFood(@PathVariable long foodId, @RequestBody @Valid FoodDTO food) {
        foodService.updateFood(foodId, food);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
