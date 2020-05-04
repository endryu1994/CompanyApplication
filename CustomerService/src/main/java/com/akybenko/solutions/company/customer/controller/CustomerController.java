package com.akybenko.solutions.company.customer.controller;

import com.akybenko.solutions.company.customer.model.ui.CustomerRequestModel;
import com.akybenko.solutions.company.customer.model.ui.CustomerResponseModel;
import com.akybenko.solutions.company.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Customer Controller")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Add a new customer", response = CustomerResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Customer created"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 409, message = "Customer already exists")})
    @PostMapping
    public ResponseEntity<CustomerResponseModel> create(@Valid @RequestBody CustomerRequestModel request) {
        CustomerResponseModel response = customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Find customer by customerId", response = CustomerResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer information"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Customer not found")})
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseModel> get(@PathVariable String customerId) {
        CustomerResponseModel response = customerService.getByCustomerId(customerId);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update an existing customer", response = CustomerResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer updated"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Customer not found")})
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseModel> update(@PathVariable String customerId,
                                                        @Valid @RequestBody CustomerRequestModel request) {
        CustomerResponseModel response = customerService.updateByCustomerId(customerId, request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Delete a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Customer deleted"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable String customerId) {
        customerService.deleteByCustomerId(customerId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Check if customer exists", response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer exists"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> exists(@PathVariable String customerId) {
        boolean isExists = customerService.existsByCustomerId(customerId);
        return ResponseEntity.ok(isExists);
    }
}
