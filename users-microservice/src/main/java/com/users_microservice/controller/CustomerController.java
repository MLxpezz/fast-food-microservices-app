package com.users_microservice.controller;

import com.users_microservice.dto.CustomerDTO;
import com.users_microservice.dto.RegisAndLogDTO;
import com.users_microservice.http.out.UserResponseDTO;
import com.users_microservice.service.interfaces.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid RegisAndLogDTO customerData) {
        return new ResponseEntity<>(customerService.createCustomer(customerData), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody @Valid CustomerDTO customerData) {
        return new ResponseEntity<>(customerService.updateCustomer(customerId, customerData), HttpStatus.OK);
    }

    @GetMapping("/check-customer/{id}")
    public ResponseEntity<?> checkCustomerById(@PathVariable Long id) {
        boolean existsCustomer = customerService.customerExist(id);

        if (existsCustomer) {
            CustomerDTO user = customerService.getCustomer(id);

            UserResponseDTO userResponseDTO = UserResponseDTO
                    .builder()
                    .email(user.email())
                    .roles(user.roles())
                    .id(user.id())
                    .build();

            return new ResponseEntity<>(userResponseDTO, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(false , HttpStatus.NOT_FOUND);
        }
    }
}
