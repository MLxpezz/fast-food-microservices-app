package com.users_microservice.repository;

import com.users_microservice.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsCustomerEntityByEmail(String email);

    Optional<CustomerEntity> findCustomerEntityByEmail(String email);
}
