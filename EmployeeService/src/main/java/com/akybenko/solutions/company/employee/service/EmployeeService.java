package com.akybenko.solutions.company.employee.service;

import com.akybenko.solutions.company.employee.model.ui.EmployeeRequestModel;
import com.akybenko.solutions.company.employee.model.ui.EmployeeResponseModel;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

@Async("threadPoolTaskExecutor")
public interface EmployeeService {

    CompletableFuture<EmployeeResponseModel> create(EmployeeRequestModel model);
    CompletableFuture<EmployeeResponseModel> getByUsername(String username);
    CompletableFuture<EmployeeResponseModel> updateByUsername(String username, EmployeeRequestModel model);
    void deleteByUsername(String username);
    void deleteByCustomerId(String customerId);
}
