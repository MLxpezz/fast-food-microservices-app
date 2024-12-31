package com.users_microservice.utils;

import com.users_microservice.dto.CustomerDTO;
import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private final AddressMapper addressMapper;
    private final PhoneMapper phoneMapper;

    public CustomerMapper(AddressMapper addressMapper, PhoneMapper phoneMapper) {
        this.addressMapper = addressMapper;
        this.phoneMapper = phoneMapper;
    }

    public CustomerEntity customerEntityFromRequestDto(RegisAndLogDTO customerData) {
        return CustomerEntity
                .builder()
                .email(customerData.email())
                .password(customerData.password())
                .build();
    }

    public CustomerDTO dtoFromEntity(CustomerEntity customerEntity) {
        return CustomerDTO
                .builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .lastname(customerEntity.getLastname())
                .email(customerEntity.getEmail())
                .roles(
                        customerEntity
                                .getRoles()
                                .stream()
                                .map(role -> role
                                        .getRole()
                                        .name())
                                .collect(Collectors.toList())
                )
                .addressList(
                        customerEntity.getAddresses() != null
                        ? addressMapper.addressListToAddressDtoList(customerEntity.getAddresses())
                        : new ArrayList<>()
                )
                .phoneList(
                        customerEntity.getPhones() != null
                        ? phoneMapper.phoneEntitySetToPhoneDtoSet(customerEntity.getPhones()) : new HashSet<>()
                )
                .build();
    }

    public List<CustomerDTO> dtoListFromEntity(List<CustomerEntity> customerEntityList) {
        return customerEntityList
                .stream()
                .map(this::dtoFromEntity)
                .toList();
    }
}
