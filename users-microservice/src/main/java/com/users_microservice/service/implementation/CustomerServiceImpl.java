package com.users_microservice.service.implementation;

import com.users_microservice.dto.CustomerDTO;
import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.entities.AddressEntity;
import com.users_microservice.entities.CustomerEntity;
import com.users_microservice.entities.PhoneEntity;
import com.users_microservice.enums.RoleEnum;
import com.users_microservice.repository.CustomerRepository;
import com.users_microservice.service.interfaces.ICustomerService;
import com.users_microservice.service.interfaces.IRoleService;
import com.users_microservice.utils.AddressMapper;
import com.users_microservice.utils.CustomerMapper;
import com.users_microservice.utils.PhoneMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final IRoleService roleService;
    private final PhoneMapper phoneMapper;
    private final AddressMapper addressMapper;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper,
            IRoleService roleService, PhoneMapper phoneMapper, AddressMapper addressMapper
    ) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.roleService = roleService;
        this.phoneMapper = phoneMapper;
        this.addressMapper = addressMapper;
    }


    @Transactional
    @Override
    public CustomerDTO createCustomer(RegisAndLogDTO customerData) {
        CustomerEntity newCustomer = customerMapper.customerEntityFromRequestDto(customerData);
        newCustomer.setRoles(new HashSet<>(Set.of(roleService.getRole(RoleEnum.CUSTOMER))));
        return customerMapper.dtoFromEntity(customerRepository.save(newCustomer));
    }

    @Transactional
    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerEntity customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente no existe"));

        customer.setName(customerDTO.name());
        customer.setLastname(customerDTO.lastname());

        // Actualizar addresses
        if (customerDTO.addressList() != null) {
            List<AddressEntity> updatedAddresses = addressMapper.addressDtoListToAddressList(customerDTO.addressList());
            customer.getAddresses().clear(); // Limpia las relaciones anteriores
            customer.getAddresses().addAll(updatedAddresses); // Agrega las nuevas
        }

        // Actualizar phones
        if (customerDTO.phoneList() != null) {
            Set<PhoneEntity> updatedPhones = phoneMapper.phoneDtoSetToPhoneEntitySet(customerDTO.phoneList());

            customer.getPhones().forEach(phone -> phone.setCustomer(null));
            customer.getPhones().clear();

            updatedPhones.forEach(phone -> {
                phone.setCustomer(customer);
            });
            customer.getPhones().addAll(updatedPhones);
        }

        return customerMapper.dtoFromEntity(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO getCustomer(Long id) {
        CustomerEntity customerEntity = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente no existe"));
        return customerMapper.dtoFromEntity(customerEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.dtoListFromEntity(customerRepository.findAll());
    }

    @Transactional
    @Override
    public String deleteCustomer(Long id) {
        CustomerEntity customerToDelete = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente no existe"));
        customerRepository.delete(customerToDelete);
        return "Cliente eliminado correctamente";
    }
}
