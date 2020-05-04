package com.akybenko.solutions.company.customer.service;

import com.akybenko.solutions.company.customer.model.ui.CustomerRequestModel;
import com.akybenko.solutions.company.customer.model.ui.CustomerResponseModel;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

@Async("threadPoolTaskExecutor")
public interface CustomerService {

    CompletableFuture<CustomerResponseModel> create(CustomerRequestModel model);
    CompletableFuture<CustomerResponseModel> getByCustomerId(String customerId);
    CompletableFuture<CustomerResponseModel> updateByCustomerId(String customerId, CustomerRequestModel model);
    void deleteByCustomerId(String customerId);
    CompletableFuture<Boolean> existsByCustomerId(String customerId);
}
