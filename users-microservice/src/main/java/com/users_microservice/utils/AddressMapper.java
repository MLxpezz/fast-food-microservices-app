package com.users_microservice.utils;

import com.users_microservice.dto.AddressDTO;
import com.users_microservice.entities.AddressEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {

    public AddressDTO addressToAddressDto(AddressEntity address) {
        return AddressDTO
                .builder()
                .city(address.getCity())
                .zip(address.getZip())
                .state(address.getState())
                .country(address.getCountry())
                .street(address.getStreet())
                .build();
    }

    public List<AddressDTO> addressListToAddressDtoList(List<AddressEntity> addresses) {
        return addresses != null ? addresses
                .stream()
                .map(this::addressToAddressDto)
                .toList() : new ArrayList<>();
    }

    public AddressEntity addressDtoToAddress(AddressDTO address) {
        return AddressEntity
                .builder()
                .city(address.city())
                .zip(address.zip())
                .state(address.state())
                .country(address.country())
                .street(address.street())
                .build();
    }

    public List<AddressEntity> addressDtoListToAddressList(List<AddressDTO> addresses) {
        return addresses != null ? addresses
                .stream()
                .map(this::addressDtoToAddress)
                .toList() : new ArrayList<>();
    }
}
