package com.akybenko.solutions.company.employee.controller;

import com.akybenko.solutions.company.employee.model.ui.EmployeeRequestModel;
import com.akybenko.solutions.company.employee.model.ui.EmployeeResponseModel;
import com.akybenko.solutions.company.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Employee Controller")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Add a new employee", response = EmployeeResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee created"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 409, message = "Employee already exists")})
    @PostMapping
    public ResponseEntity<EmployeeResponseModel> create(@Valid @RequestBody EmployeeRequestModel request) {
        EmployeeResponseModel response = employeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Find employee by customerId", response = EmployeeResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee information"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Employee not found")})
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> get(@PathVariable String employeeId) {
        EmployeeResponseModel response = employeeService.getByUsername(employeeId);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update an existing employee", response = EmployeeResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee updated"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Employee not found")})
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> update(@PathVariable String employeeId,
                                                        @Valid @RequestBody EmployeeRequestModel request) {
        EmployeeResponseModel response = employeeService.updateByUsername(employeeId, request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Delete an employee")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Employee deleted"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> delete(@PathVariable String employeeId) {
        employeeService.deleteByUsername(employeeId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete customer employees")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Customer employees deleted"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAll(@RequestParam String customerId) {
        employeeService.deleteByCustomerId(customerId);
        return ResponseEntity.noContent().build();
    }
}
