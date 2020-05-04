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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final EmployeeServiceProxy employeeServiceProxy;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               EmployeeServiceProxy employeeServiceProxy) {
        this.customerRepository = customerRepository;
        this.employeeServiceProxy = employeeServiceProxy;
    }

    @Override
    public CompletableFuture<CustomerResponseModel> create(CustomerRequestModel model) {
        LOG.info("[CustomerServiceImpl#create] Object for save={}", model);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerEntity entity = mapper.map(model, CustomerEntity.class);
        entity.setCustomerId(UUID.randomUUID().toString());
        LOG.info("[CustomerServiceImpl#create] Entity for save={}", entity);
        CustomerEntity created = customerRepository.save(entity);
        CustomerResponseModel response = mapper.map(created, CustomerResponseModel.class);
        LOG.info("[CustomerServiceImpl#create] Response={}", response);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<CustomerResponseModel> getByCustomerId(String customerId) {
        LOG.info("[CustomerServiceImpl#getByCustomerId] CustomerID={}", customerId);
        CustomerEntity entity = customerRepository.findByCustomerId(customerId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Customer with id = %s not found", customerId)));
        LOG.info("[CustomerServiceImpl#getByCustomerId] Entity from DB={}", entity);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerResponseModel response = mapper.map(entity, CustomerResponseModel.class);
        LOG.info("[CustomerServiceImpl#getByCustomerId] Response={}", response);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<CustomerResponseModel> updateByCustomerId(String customerId, CustomerRequestModel model) {
        LOG.info("[CustomerServiceImpl#updateByCustomerId] CustomerID={}, Object for update={}", customerId, model);
        CustomerEntity entity = customerRepository.findByCustomerId(customerId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Customer with id = %s not found", customerId)));
        LOG.info("[CustomerServiceImpl#updateByCustomerId] Entity from DB={}", entity);
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());
        customerRepository.save(entity);
        LOG.info("[CustomerServiceImpl#updateByCustomerId] Updated entity={}", entity);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerResponseModel response = mapper.map(entity, CustomerResponseModel.class);
        LOG.info("[CustomerServiceImpl#updateByCustomerId] Response={}", response);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        LOG.info("[CustomerServiceImpl#deleteByCustomerId] CustomerID={}", customerId);
        CompletableFuture.completedFuture(employeeServiceProxy.deleteAll(customerId))
                .thenAccept(result -> customerRepository.deleteByCustomerId(customerId));
        LOG.info("[CustomerServiceImpl#deleteByCustomerId] Customer and his employees deleted");
    }

    @Override
    public CompletableFuture<Boolean> existsByCustomerId(String customerId) {
        LOG.info("[CustomerServiceImpl#existsByCustomerId] CustomerID={}", customerId);
        boolean response = customerRepository.existsByCustomerId(customerId);
        LOG.info("[CustomerServiceImpl#existsByCustomerId] Response={}", response);
        return CompletableFuture.completedFuture(response);
    }
}
