package com.food_microservice.enums;

import java.util.Arrays;

public enum TypeFoodEnum {

    PIZZA,
    HAMBURGER,
    HOTDOG,
    BONELESS,
    WINGS;

    public static TypeFoodEnum fromString(String enumName) {
        return Arrays.stream(TypeFoodEnum.values())
                .filter(typeFood -> {
                    return typeFood.name().equalsIgnoreCase(enumName);
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid name for type food: " + enumName));
    }
}
