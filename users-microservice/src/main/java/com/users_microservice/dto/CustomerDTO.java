package com.users_microservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record CustomerDTO(
        Long id,
        String name,
        String lastname,
        String email,
        List<String> roles,
        @NotNull(message = "El campo es requerido")
        Set<PhoneDTO> phoneList,
        @NotNull(message = "El campo es requerido")
        List<AddressDTO> addressList
) {
}
