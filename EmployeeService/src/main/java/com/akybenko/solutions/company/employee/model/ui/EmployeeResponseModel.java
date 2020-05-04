package com.akybenko.solutions.company.employee.model.ui;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmployeeResponseModel {

    @ApiModelProperty(notes = "Username of the employee", example = "emp_uname")
    private String username;
    @ApiModelProperty(notes = "ID of the customer", example = "123e4567-e89b-12d3-a456-426655440000")
    private String customerId;
    @ApiModelProperty(notes = "First name of the employee", example = "Roy")
    private String firstName;
    @ApiModelProperty(notes = "Last name of the employee", example = "Jones")
    private String lastName;
    @ApiModelProperty(notes = "Email of the employee", example = "royjones@example.com")
    private String email;
}
