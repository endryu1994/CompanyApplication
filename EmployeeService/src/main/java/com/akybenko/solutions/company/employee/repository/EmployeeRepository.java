package com.akybenko.solutions.company.employee.repository;

import com.akybenko.solutions.company.employee.model.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByUsername(String username);
    void deleteByUsername(String username);
    void deleteAllByCustomerId(String customerId);
}
