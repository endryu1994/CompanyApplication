package com.akybenko.solutions.company.customer.service.impl;

import com.akybenko.solutions.company.customer.exception.EntityNotFoundException;
import com.akybenko.solutions.company.customer.feign.EmployeeServiceProxy;
import com.akybenko.solutions.company.customer.model.entity.CustomerEntity;
import com.akybenko.solutions.company.customer.model.ui.CustomerRequestModel;
import com.akybenko.solutions.company.customer.model.ui.CustomerResponseModel;
import com.akybenko.solutions.company.customer.repository.CustomerRepository;
import com.akybenko.solutions.company.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeServiceProxy employeeServiceProxy;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               EmployeeServiceProxy employeeServiceProxy) {
        this.customerRepository = customerRepository;
        this.employeeServiceProxy = employeeServiceProxy;
    }

    @Override
    public CustomerResponseModel create(CustomerRequestModel model) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerEntity entity = mapper.map(model, CustomerEntity.class);
        entity.setCustomerId(UUID.randomUUID().toString());
        CustomerEntity created = customerRepository.save(entity);
        return mapper.map(created, CustomerResponseModel.class);
    }

    @Override
    public CustomerResponseModel getByCustomerId(String customerId) {
        CustomerEntity entity = customerRepository.findByCustomerId(customerId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Customer with id = %s not found", customerId)));
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(entity, CustomerResponseModel.class);
    }

    @Override
    public CustomerResponseModel updateByCustomerId(String customerId, CustomerRequestModel model) {
        CustomerEntity entity = customerRepository.findByCustomerId(customerId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Customer with id = %s not found", customerId)));
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());
        customerRepository.save(entity);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(entity, CustomerResponseModel.class);
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        employeeServiceProxy.deleteAll(customerId);
        customerRepository.deleteByCustomerId(customerId);
    }

    @Override
    public boolean existsByCustomerId(String customerId) {
        return customerRepository.existsByCustomerId(customerId);
    }
}
