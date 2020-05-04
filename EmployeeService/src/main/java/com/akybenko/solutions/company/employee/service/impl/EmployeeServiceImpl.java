package com.akybenko.solutions.company.employee.service.impl;

import com.akybenko.solutions.company.employee.exception.EntityNotFoundException;
import com.akybenko.solutions.company.employee.feign.CustomerServiceProxy;
import com.akybenko.solutions.company.employee.model.entity.EmployeeEntity;
import com.akybenko.solutions.company.employee.model.ui.EmployeeRequestModel;
import com.akybenko.solutions.company.employee.model.ui.EmployeeResponseModel;
import com.akybenko.solutions.company.employee.repository.EmployeeRepository;
import com.akybenko.solutions.company.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final CustomerServiceProxy customerServiceProxy;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               CustomerServiceProxy customerServiceProxy) {
        this.employeeRepository = employeeRepository;
        this.customerServiceProxy = customerServiceProxy;
    }

    @Override
    public CompletableFuture<EmployeeResponseModel> create(EmployeeRequestModel model) {
        LOG.info("[EmployeeServiceImpl#create] Object for save={}", model);
        String customerId = model.getCustomerId();
        Boolean isCustomerExists = customerServiceProxy.isCustomerExists(customerId).getBody();
        LOG.info("[EmployeeServiceImpl#create] Customer exists={}", isCustomerExists);
        if (!Objects.requireNonNull(isCustomerExists)) {
            throw new EntityNotFoundException(String.format("Customer with id = %s not found", customerId));
        }
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeEntity entity = mapper.map(model, EmployeeEntity.class);
        employeeRepository.save(entity);
        EmployeeResponseModel response = mapper.map(entity, EmployeeResponseModel.class);
        LOG.info("[EmployeeServiceImpl#create] Response={}", response);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<EmployeeResponseModel> getByUsername(String username) {
        LOG.info("[EmployeeServiceImpl#getByUsername] Username={}", username);
        EmployeeEntity entity = employeeRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with username = %s not found", username)));
        LOG.info("[EmployeeServiceImpl#getByUsername] Entity from DB={}", entity);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeResponseModel response = mapper.map(entity, EmployeeResponseModel.class);
        LOG.info("[EmployeeServiceImpl#getByUsername] Response={}", response);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<EmployeeResponseModel> updateByUsername(String username, EmployeeRequestModel model) {
        LOG.info("[EmployeeServiceImpl#updateByUsername] Username={}, Object for update={}", username, model);
        String customerId = model.getCustomerId();
        Boolean isCustomerExists = customerServiceProxy.isCustomerExists(customerId).getBody();
        LOG.info("[EmployeeServiceImpl#updateByUsername] Customer exists={}", isCustomerExists);
        if (!Objects.requireNonNull(isCustomerExists)) {
            throw new EntityNotFoundException(String.format("Customer with id = %s not found", customerId));
        }
        EmployeeEntity entity = employeeRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with username = %s not found", username)));
        LOG.info("[EmployeeServiceImpl#updateByUsername] Entity from DB={}", entity);
        entity.setUsername(model.getUsername());
        entity.setCustomerId(model.getCustomerId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setEmail(model.getEmail());
        employeeRepository.save(entity);
        LOG.info("[EmployeeServiceImpl#updateByUsername] Updated entity={}", entity);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeResponseModel response = mapper.map(entity, EmployeeResponseModel.class);
        LOG.info("[EmployeeServiceImpl#updateByUsername] Response={}", response);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    public void deleteByUsername(String username) {
        LOG.info("[EmployeeServiceImpl#deleteByUsername] Username={}", username);
        employeeRepository.deleteByUsername(username);
        LOG.info("[EmployeeServiceImpl#deleteByUsername] Employee deleted");
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        LOG.info("[EmployeeServiceImpl#deleteByCustomerId] CustomerID={}", customerId);
        employeeRepository.deleteAllByCustomerId(customerId);
        LOG.info("[EmployeeServiceImpl#deleteByCustomerId] Employees deleted");
    }
}
