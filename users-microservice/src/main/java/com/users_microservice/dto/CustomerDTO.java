package com.users_microservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomerDTO(
        String name,
        String lastname,
        List<PhoneDTO> phoneList,
        List<AddressDTO> addressList
) {
}
