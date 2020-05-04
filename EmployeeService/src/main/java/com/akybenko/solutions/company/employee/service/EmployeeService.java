package com.akybenko.solutions.company.employee.service;

import com.akybenko.solutions.company.employee.model.ui.EmployeeRequestModel;
import com.akybenko.solutions.company.employee.model.ui.EmployeeResponseModel;

public interface EmployeeService {

    EmployeeResponseModel create(EmployeeRequestModel model);
    EmployeeResponseModel getByUsername(String username);
    EmployeeResponseModel updateByUsername(String username, EmployeeRequestModel model);
    void deleteByUsername(String username);
    void deleteByCustomerId(String customerId);
}
