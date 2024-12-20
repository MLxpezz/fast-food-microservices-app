package com.users_microservice.service.interfaces;

import com.users_microservice.dto.PhoneDTO;

import java.util.List;

public interface IPhoneService {

    void savePhone(String phone);

    List<PhoneDTO> getPhones();

    void deletePhone(Long id);

    void updatePhone(Long id, PhoneDTO phoneDTO);
}
