package com.akybenko.solutions.company.customer.repository;

import com.akybenko.solutions.company.customer.model.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCustomerId(String customerId);
    void deleteByCustomerId(String customerId);
    boolean existsByCustomerId(String customerId);
}
