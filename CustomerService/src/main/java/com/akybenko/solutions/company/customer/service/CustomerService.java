package com.akybenko.solutions.company.customer.service;

import com.akybenko.solutions.company.customer.model.ui.CustomerRequestModel;
import com.akybenko.solutions.company.customer.model.ui.CustomerResponseModel;

public interface CustomerService {

    CustomerResponseModel create(CustomerRequestModel model);
    CustomerResponseModel getByCustomerId(String customerId);
    CustomerResponseModel updateByCustomerId(String customerId, CustomerRequestModel model);
    void deleteByCustomerId(String customerId);
    boolean existsByCustomerId(String customerId);
}
