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
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CustomerServiceProxy customerServiceProxy;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               CustomerServiceProxy customerServiceProxy) {
        this.employeeRepository = employeeRepository;
        this.customerServiceProxy = customerServiceProxy;
    }

    @Override
    public EmployeeResponseModel create(EmployeeRequestModel model) {
        String customerId = model.getCustomerId();
        Boolean isCustomerExists = customerServiceProxy.isCustomerExists(customerId).getBody();
        if (!Objects.requireNonNull(isCustomerExists)) {
            throw new EntityNotFoundException(String.format("Customer with id = %s not found", customerId));
        }
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeEntity entity = mapper.map(model, EmployeeEntity.class);
        employeeRepository.save(entity);
        return mapper.map(entity, EmployeeResponseModel.class);
    }

    @Override
    public EmployeeResponseModel getByUsername(String username) {
        EmployeeEntity entity = employeeRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with username = %s not found", username)));
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(entity, EmployeeResponseModel.class);
    }

    @Override
    public EmployeeResponseModel updateByUsername(String username, EmployeeRequestModel model) {
        String customerId = model.getCustomerId();
        Boolean isCustomerExists = customerServiceProxy.isCustomerExists(customerId).getBody();
        if (!Objects.requireNonNull(isCustomerExists)) {
            throw new EntityNotFoundException(String.format("Customer with id = %s not found", customerId));
        }
        EmployeeEntity entity = employeeRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with username = %s not found", username)));
        entity.setUsername(model.getUsername());
        entity.setCustomerId(model.getCustomerId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setEmail(model.getEmail());
        employeeRepository.save(entity);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(entity, EmployeeResponseModel.class);
    }

    @Override
    public void deleteByUsername(String username) {
        employeeRepository.deleteByUsername(username);
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        employeeRepository.deleteAllByCustomerId(customerId);
    }
}
