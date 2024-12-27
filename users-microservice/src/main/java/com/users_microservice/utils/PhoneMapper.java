package com.users_microservice.utils;

import com.users_microservice.dto.PhoneDTO;
import com.users_microservice.entities.PhoneEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PhoneMapper {

    public PhoneDTO phoneToPhoneDto(PhoneEntity phone) {
        return PhoneDTO
                .builder()
                .phone(phone.getPhone())
                .build();
    }

    public Set<PhoneDTO> phoneEntitySetToPhoneDtoSet(Set<PhoneEntity> phoneList) {
        return phoneList
                .stream()
                .map(this::phoneToPhoneDto)
                .collect(Collectors.toSet());
    }

    public PhoneEntity phoneDtoToPhoneEntity(PhoneDTO phoneDTO) {
        return PhoneEntity
                .builder()
                .phone(phoneDTO.phone())
                .build();
    }

    public Set<PhoneEntity> phoneDtoSetToPhoneEntitySet(Set<PhoneDTO> phoneDTOSet) {
        return phoneDTOSet
                .stream().map(this::phoneDtoToPhoneEntity)
                .collect(Collectors.toSet());
    }
}
