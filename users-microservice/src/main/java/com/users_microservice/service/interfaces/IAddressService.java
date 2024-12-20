package com.users_microservice.service.interfaces;

import com.users_microservice.dto.AddressDTO;

import java.util.List;

public interface IAddressService {

    void saveAddress(String address);

    List<AddressDTO> getAddresses();

    void updateAddress(Long id, AddressDTO address);

    void deleteAddress(Long id);
}
