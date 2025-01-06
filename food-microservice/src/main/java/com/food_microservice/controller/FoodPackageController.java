package com.food_microservice.controller;

import com.food_microservice.dto.FoodPackageDTO;
import com.food_microservice.service.interfaces.IFoodPackageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/food-package")
public class FoodPackageController {

    private final IFoodPackageService foodPackageService;

    public FoodPackageController(IFoodPackageService foodPackageService) {
        this.foodPackageService = foodPackageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFoodPackage(@RequestBody @Valid FoodPackageDTO foodPackage) {
        foodPackageService.createFoodPackage(foodPackage);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllFoodPackages() {
        return new ResponseEntity<>(foodPackageService.getFoodPackages(), HttpStatus.OK);
    }

    @GetMapping("/get/{packageId}")
    public ResponseEntity<?> getFoodPackageById(@PathVariable long packageId) {
        return new ResponseEntity<>(foodPackageService.getFoodPackage(packageId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{packageId}")
    public ResponseEntity<?> deleteFoodPackage(@PathVariable long packageId) {
        foodPackageService.deleteFoodPackage(packageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{packageId}")
    public ResponseEntity<?> updateFoodPackage(@PathVariable long packageId, @RequestBody @Valid FoodPackageDTO foodPackage) {
        foodPackageService.updateFoodPackage(packageId, foodPackage);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
