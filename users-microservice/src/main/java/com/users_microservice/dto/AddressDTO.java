package com.users_microservice.dto;

import lombok.Builder;

@Builder
public record AddressDTO(
        String street,
        String city,
        String state,
        String zip,
        String country
) {
}
