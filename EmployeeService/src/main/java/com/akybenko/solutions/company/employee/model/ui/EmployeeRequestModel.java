package com.akybenko.solutions.company.employee.model.ui;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EmployeeRequestModel {

    @ApiModelProperty(notes = "Username of the employee", example = "emp_uname", required = true)
    @NotNull(message = "Username cannot be null")
    @Size(min = 5, message = "First name must not be less than 5 characters")
    private String username;
    @ApiModelProperty(notes = "ID of the customer", example = "123e4567-e89b-12d3-a456-426655440000", required = true)
    @NotNull(message = "CustomerId cannot be null")
    private String customerId;
    @ApiModelProperty(notes = "First name of the employee", example = "Roy", required = true)
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    private String firstName;
    @ApiModelProperty(notes = "Last name of the employee", example = "Jones", required = true)
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    private String lastName;
    @ApiModelProperty(notes = "Email of the employee", example = "royjones@example.com", required = true)
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email address")
    private String email;
}
