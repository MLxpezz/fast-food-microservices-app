package com.users_microservice.service.interfaces;

import com.users_microservice.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO getCustomer(Long id);

    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(Long id);
}
