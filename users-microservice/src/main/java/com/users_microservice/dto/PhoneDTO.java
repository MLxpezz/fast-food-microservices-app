package com.users_microservice.dto;

import lombok.Builder;

@Builder
public record PhoneDTO(
        String phone
) {
}
