package com.users_microservice.service.interfaces;

import com.users_microservice.dto.CustomerDTO;
import com.users_microservice.dto.RegisAndLogDTO;

import java.util.List;

public interface ICustomerService {

    CustomerDTO createCustomer(RegisAndLogDTO customerData);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO getCustomer(Long id);

    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(Long id);

    boolean customerExist(Long id);
}
